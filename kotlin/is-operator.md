## is연산자를 when에 사용하기

```kotlin
val person: Person
...
when(person){
    is Person -> {}
    is Student -> {}
    is Professor -> {}
}
```