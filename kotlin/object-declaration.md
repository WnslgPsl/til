## 객체 선언(Object Declaration)

```kotlin
object Person {

    var name: String = ""
    var age: Int = 0

    fun print() {
        println(name)
        println(age)
    }
}

fun main(args: Array<String>) {

    Person.name = "Singleton"
    Person.age = 45
    Person.print()
    
}

결과값
Singleton
45
```

```kotlin
// 다음 코드도 가능
val person: Person = Person
```

> object 키워드 덕분에 자바에서 작성하던 싱글톤 패턴 코드를 더이상 쓰지 않아도 된다.