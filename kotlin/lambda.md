## 람다

* 람다식은 항상 중괄호 {}로 묶여있다. 생략하지 않은 형태는 다음과 같다
```kotlin
val sum: (Int, Int) -> Int = {x: Int, y: Int -> x + y}
```
> (Int, Int) -> Int 에서 앞쪽은 파라미터를 나타내며 -> 뒷쪽은 body를 나타낸다.</br>
만약 -> 뒤 리턴 타입이 Unit이 아닌 경우에는 람다 body 내의 마지막 표현식이 리턴 값이 된다.

</br>

* 람다식의 완전한 문법적 형태는 다음과 같다.
```kotlin
val sum = {x, y -> x + y}
```
</br>

* 함수이기 때문에 함수명(파라미터) 형태로 사용할 수 있다.
```kotlin
sum(1, 2)

결과값 
3
```
</br>

* Kotlin에서는 파리미터가 하나뿐이라면 it이라는 키워드로 대체할 수 있다.
```kotlin
val add: (Int) -> Int = {x: Int -> x + 1}
// x:Int -> x 가 it으로 대체
val add: (Int) -> Int = {it + 1}

add(1)

결과값
2
```
</br>

* qualified return syntex를 사용하여 명시적으로 리턴을 선언할 수 있다. 그렇지 않으면 마지막 표현식 값이 암시적으로 리턴된다.
```kotlin
val returnSkip: (Int) -> Boolean = {
    val check = it > 10
    check
}

val returnExist: (Int) -> Boolean = num@{
    val check = it > 10
    return@num check
}

println(returnSkip(11))
println(returnExist(1))

결과값
true
false
```
</br>

* 람다는 다음 규칙에 따라 정의한다.
> 1. 람다 함수는 항상 {}으로 감싸서 표현해야 한다.
> 2. {} 안에 -> 표시가 있으며 -> 왼쪽은 매개변수, 오른쪽은 함수 내용이다.
> 3. 매개변수 타입을 선언해야 하며 추론할 수 있을 때는 생략할 수 있다.
> 4. 함수의 반환값은 함수 내용의 마지막 표현식이다.