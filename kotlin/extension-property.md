## 확장 프로퍼티(Extension Property)

```kotlin
val String.isLarge: Boolean
    get() = this.length >= 3

fun main(args: Array<String>) {
    var a = "Kotlin"
    println(a.getLength())
}

결과값 true
```