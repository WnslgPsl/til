## 동반자 객체(Companion Object)

클래스 안에 포함되는 이름 없는 객체, 어떤 클래스의 모든 인스턴스가 공유하는 객체를 만들고 싶을 때 사용

```kotlin
class Person private constructor() {
    companion object {

        var countCreated = 0
            private set

        fun create(): Person {
            countCreated += 1
            return Person()
        }
    }
}

fun main(args: Array<String>) {
    Person.create()
    Person.create()
    println(Person.countCreated)
}

결과값
2
```

* create 멤버 함수를 통해서만 Person 객체를 생성할 수 있도록 하기 위해 생성자의 접근지정자를 private으로 지정
* 외부로부터 값을 조작하는 것을 방지하기 위해 setter의 접근 지정자를 private으로 지정

> 어떤 클래스 안에 동반자 객체를 정의해놓으면 Companion이라는 식별자가 자동으로 생긴다. 즉 Person.create()는 Person.Companion.create()로도 호출할 수 있다.</br>

> 코틀린에는 static 키워드가 더 이상 존재하지 않는다. 따라서 static의 효과를 얻고 싶으면 동반자 객체를 새용해야 한다.