## 확장 프로퍼티(Extension Property)

```kotlin
val String.isLarge: Boolean
    get() = this.length >= 3

fun main(args: Array<String>) {
    var a = "Kotlin"
    println(a.isLarge)
}

결과값 true
```

> 확장 프로퍼티에는 Field가 존재하지 않는다. 따라서 field 식별자는 사용할 수 없다.