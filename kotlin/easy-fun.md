# 함수를 호출하기 쉽게 만들기

## 이름 붙인 인자

```kotlin
joinToString(collection, "", "", ".")
```

함수의 시그니처를 살펴보지 않고는 함수의 인자값을 파악하기 어렵다.

```kotlin
joinToString(collection, separator = "", prefix = "", postfix = ".")
```
daslkdf
