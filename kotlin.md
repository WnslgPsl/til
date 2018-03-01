# NullSafety 예제

## 널 허용 여부 표기

* 코틀린은 별도 표기가 없을 경우 널 값을 허용하지 않는다.  
  널 값을 가질 수 있도록 하려면 명시적으로 타입 뒤에 ?를 붙여줘야 한다.  
```kotlin
val nullableString : String? = null
val nonNullString : String = "Foo"
```

* 코틀린은 널 값을 허용하지 않는 값을 초기화하지 않거나,  
  null을 대입하면 컴파일 오류를 발생시킨다.
```kotlin
val name : String            // 오류 : 값이 초기화되지 않음
val address : String = null  // 오류 : null을 허용하지 않는 값에 null 대입 불가
```

* 함수의 파라미터나 반환 값에도 동일하게 적용
```kotlin
// 인자 line2에는 null 사용가능
fun formatAddress(line1: String, line2: String?, city: String) : String {
  ...
}
// 입력한 주소에 해당하는 우편번호를 반환하지만, 검색 결과가 없을 경우 null 반환
fun findPostalCode(address : String) : PostalCode? {
  ...
}
```

* 변수와 마찬가지로 함수의 파라미터나 반환 값에 올바르지 않은 타입을 사용하면 컴파일 오류 발생
```kotlin
// 오류 : 인자 line1은 널 값을 허용하지 않음
formatAddress(null, null, "San Francisco")
// 오류 : 값 postalCode는 널 값을 허용하지 않으나 findPostalCode 함수는 널 값을 반환 가능
val postal : PostalCode = findPostalCode("1600 Amphitheatre Pkwy")
```

## 널 값을 대신하는 방법 : 엘비스(?:)연산자

* 널 값을 허용하지 않는 값 혹은 변수에 널 값을 반환 할 수 있는 함수의 결과를 대입해야 하는 경우  
  이에 대한 처리를 별도로 해야한다. 엘비스 연산자를 사용하면 이를 편리하게 처리할 수 있다.
```kotlin

```