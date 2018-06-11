## 확장 함수(Extension Function)

1. String은 코틀린에 내장된 클래스이기 때문에 우리가 마음대로 멤버 함수를 추가할 수 없다.
2. open 키워드가 붙어있지 않아 상속도 불가능하다.
3. 확장함수는 상속 없이 클래스 외부에서 멤버 함수를 추가할 수 있다.

```kotlin
fun String?.isLength(): Int {
    return this?.length?:0
}

fun main(args: Array<String>) {
    var a = "Kotlin"
    println(a.isLength())
}

결과값 3
```