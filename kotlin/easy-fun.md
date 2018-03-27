# 함수를 호출하기 쉽게 만들기

## 이름 붙인 인자
</br>
* 일반적인 java에서 함수 호출을 보면

```kotlin
joinToString(collection, "", "", ".")
```

* 함수의 시그니처를 살펴보지 않고는 함수의 인자값을 파악하기 어렵다. 코틀린에서는 다음과 같다.

```kotlin
// 이름을 붙인 인자
joinToString(collection, separator = "", prefix = "", postfix = ".")
```

* 코틀린으로 작성한 함수를 호출할 때는 인자 중 일부 또는 전부의 이름을 명시할 수 있다. 인자 중 어느 하나라도 이름을 명시하고 나면 혼동을 막기위해 그 뒤에 오는 모든 인자는  
이름을 꼭 명시해야 한다.

</br>
## 디폴트 파라미터 값
</br>
* 자바에서는 일부 클래스에서 오버로딩한 메소드가 너무 많아진다는 문제가 있다.</br>
코틀린에서는 함수 선언에서 파라미터의 디폴트 값을 지정할 수 있으므로 이런 오버로드 중 상당수를 피할 수 있다.

```kotlin
fun <T> joinToString(
    collection: Collection<T>,
    separator: String = ",",
    prefix: String = "",
    postfix: String = ""
) : String
```
* 이제 함수를 호출할 때 모든 인자를 쓸 수도 있고, 일부를 생략할 수도 있다. 

```kotlin
joinToString(list, ",", "", "")
1, 2, 3

joinToString(list)
1, 2, 3

joinToString(list,";")
1; 2; 3
```

* 일반 호출 문법을 사용하려면 함수를 선언할 때와 같은 순서로 인자를 지정해야 한다.
이런 경우 인자 목록의 중간에 있는 인자를 생략하고 지정하고 싶은 인자에 이름을 붙여서 순서와 관계없이 지정할 수 있다.

```kotlin
joinToString(list, postfix=";", prefix = "%")
# 1, 2, 3;
```
</br>
> 디폴트 값과 자바
* 자바에서는 디폴트 파라미터 값이라는 개념이 없어서 코틀린 함수를 자바에서 호출하는 경우 그 함수가 디폴트 파라미터 값을 제공하더라도 모든 인자를 명시해야 한다. 그럴 때 @JvmOverloads 어노테이션을 함수에 추가하면 코틀린 컴파일러가 자동으로 맨 마지막 파라미터를 하나씩 생략한 오버로딩한 자바 메소드를 추가해 준다.

```java
// 예를들어 joinToString에 @JvmOverloads를 붙이면 다음과 같은 오버로딩한 함수가 만들어진다.

/* 자바 */
String joinToString(Collection<T> collection, String separator, 
    String prefix, String postfix);
    
String joinToString(Collection<T> collection, String separator, 
    String prefix);
    
String joinToString(Collection<T> collection, String separator);

String joinToString(Collection<T> collection);

각각의 오버로딩한 함수들은 시그니처에서 생략된 파라미터에 대해 코틀린 함수의 디폴트 파라미터 값을 사용한다.

```
