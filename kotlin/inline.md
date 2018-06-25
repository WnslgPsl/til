## inline 함수

함수 호출문이 함수 속에 들어있는 문장으로 대체된다.

```kotlin
inline fun hello() {
    println("Hello")
    println("Kotlin")
}

fun main(args: Array<String>) {

    hello()
    hello()
    hello()

}

결과값
Hello
Kotlin
Hello
Kotlin
Hello
Kotlin
```

inline 함수를 호출하면 함수 호출문들은 컴파일 되는 순간 다음처럼 코드가 대체된다.

```kotlin
fun main(args: Array<String>) {

    println("Hello")
    println("Kotlin")
    println("Hello")
    println("Kotlin")
    println("Hello")
    println("Kotlin")

}
```

* inline 함수는 함수 속의 문장을 재활용하지 않기 때문에, 문장이 많은 함수를 inline으로 바꾸면 프로그램의 크기가 기하급수적으로 늘어난다.

* 문작이 적고 빈번히 호출되는 함수만 inline으로 만들 것을 권장한다.

> inline 함수는 재귀호출이 불가능하다. 함수 몸체 코드가 무한대로 늘어날 수 있기 때문이다.