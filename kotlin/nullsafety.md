# NullSafety 예제

## 널 허용 여부 표기

* 코틀린은 별도 표기가 없을 경우 널 값을 허용하지 않는다.  
  널 값을 가질 수 있도록 하려면 명시적으로 타입 뒤에 ?를 붙여줘야 한다.  
```kotlin
val nullableString : String? = null
val nonNullString : String = "Foo"
```

* 코틀린은 널 값을 허용하지 않는 값을 초기화하지 않거나,  
  null을 대입하면 컴파일 오류를 발생시킨다.
```kotlin
val name : String            // 오류 : 값이 초기화되지 않음
val address : String = null  // 오류 : null을 허용하지 않는 값에 null 대입 불가
```

* 함수의 파라미터나 반환 값에도 동일하게 적용  
```kotlin
// 인자 line2에는 null 사용가능
fun formatAddress(line1: String, line2: String?, city: String) : String {
  ...
}
// 입력한 주소에 해당하는 우편번호를 반환하지만, 검색 결과가 없을 경우 null 반환
fun findPostalCode(address : String) : PostalCode? {
  ...
}
```

## 널 값을 대신하는 방법 : 엘비스(?:)연산자

* 널 값을 허용하지 않는 값 혹은 변수에 널 값을 반환 할 수 있는 함수의 결과를 대입해야 하는 경우,</br>
  이에대한 처리를 별도로 해야한다.
```kotlin
// foo가 null이 아닐 경우 foo를, null이면 bar를 반환
foo ?: bar
```
```kotlin
// 함수가 널 값을 반환하는 경우 PostalCode.NONE 값을 대입한다.
val postal : PostalCode = findPostalCode("1600 Amphitheatre Pkwy") ?: PostalCode.NONE
```

* 엘비스 연산자의 예
```java
    public Image generateMapImage(String address){
    
      PostalCode postal = findPostalCode(address);
      
      if(postal == null){
        return null;
      }
      
      // 지도 이미지 생성
      ...
    }
```
```kotlin
    fun generateMapImage(address: String){
      val postal = findPostalCode(address) ?: return null
      
      // 지도 이미지 생성
      ...
    }
```

* 값을 반환하는 대신 예외가 발생하도록 할 수도 있다.
```kotlin
    fun generateMapImage(address: String){
      val postal = findPostalCode(address) ?: throw IllegalStateException()
      
      // 지도 이미지 생성
      ...
    }
```

## 널 값 확인과 처리를 한번에 : 안전한 호출(?.)연산자

* 널 값을 확인과 값 접근/함수호출을 한번에 할 수 있다.  
  안전한 호출 연산자는 이 연산자를 사용하는 객체가 널 값이 아닌 경우에  
  연산자 뒤의 문장을 수행한다. 널 값일 경우에는 뒤의 문장을 수행하지 않고 널 값을 반환한다.  
  따라서 널 값인 객체의 프로퍼티를 참조하거나 함수를 호출하는 일을 방지할 수 있다.
```kotlin
    // bar 가 null이 아닐 경우에만 해당 값을 대입, 그렇지 않은 경우 null을 foo에 대입
    val foo = bar?.baz
    
    // foo가 null이 아닐 경우에만 bar() 호출
    foo?.bar()
```

* 엘비스 연산자를 함께 사용하면 널 값을 반환할 때, 대신 사용할 값을 지정할 수 있다.
```java
    // java
    
    Contact contact = ... // 주소록 항목 객체
    String line;
    
    if(contact.address != null && contact.address.line2 != null) {
      line = contact.address.line2;
    }else{
      line = "No address"
    }
```
```kotlin
    // kotlin
    
    val contact : Contact = ... // 주소록 항목 객체
    
    //주소가 없거나 line2가 없을 경우 기본값인 "No address" 반환
    val line : String = contact.address?.line2 ?: "No address"
```

## 안전한 자료형 변환 : as?연산자



