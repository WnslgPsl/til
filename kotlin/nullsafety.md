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

## 널 값을 대신하는 방법 : 엘비스(?:)연산자

* 널 값을 허용하지 않는 값 혹은 변수에 널 값을 반환 할 수 있는 함수의 결과를 대입해야 하는 경우,</br>
  이에대한 처리를 별도로 해야한다.
```kotlin
// foo가 null이 아닐 경우 foo를, null이면 bar를 반환
foo ?: bar
```
```kotlin
// 함수가 널 값을 반환하는 경우 PostalCode.NONE 값을 대입한다.
val postal : PostalCode = findPostalCode("1600 Amphitheatre Pkwy") ?: PostalCode.NONE
```

* 엘비스 연산자의 예
```java
    public Image generateMapImage(String address){
    
      PostalCode postal = findPostalCode(address);
      
      if(postal == null){
        return null;
      }
      
      // 지도 이미지 생성
      ...
    }
```
```kotlin
    fun generateMapImage(address: String){
      val postal = findPostalCode(address) ?: return null
      
      // 지도 이미지 생성
      ...
    }
```

* 값을 반환하는 대신 예외가 발생하도록 할 수도 있다.
```kotlin
    fun generateMapImage(address: String){
      val postal = findPostalCode(address) ?: throw IllegalStateException()
      
      // 지도 이미지 생성
      ...
    }
```

## 널 값 확인과 처리를 한번에 : 안전한 호출(?.)연산자

* 

