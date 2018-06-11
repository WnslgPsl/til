## as 연산자와 다운캐스팅

```kotlin
val person: Person = Student("John", 32)
val person2: Person = Person("Jack", 29)

var person3: Student = person as Student
person3 = person2 as Student
```

1. person3은 person 참조변수가 가리키고 있는 객체는 Student 타입이므로 무사히 캐스팅 된다.
2. 마지막줄 person3은 person2 참조 변수가 Person의 인스턴스를 가리키고 있으므로 ClassCastException 예외가 발생한다.

>  캐스팅에 실패했을 때 예외가 발생하는 것을 막고 싶으면 as? 연산자를 대신 사용한다. 캐스팅에 실패하면 null을 반환