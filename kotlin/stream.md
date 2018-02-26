# Stream 예제

## Map

* 도시 이름을 대문자로 반환
```kotlin
val cities = listOf("Seoul", "Tokyo", "Changwon")
cities.map { city -> city.toUpperCase()}
        .forEach { println(it) }
```

* 도시 이름의 길이를 반환
```kotlin
val cities = listOf("Seoul", "Tokyo", "Changwon")
cities.map { city -> city.length}
        .forEach { println("length = $it") }
```

* 변환 함수에서 각 인자와 인덱스를 곱한 값을 반환
```kotlin
val numbers = 0..10
numbers.mapIndexed { idx, numbers -> idx * numbers}
          .forEach { println(it) }
```

* 1부터 시작하여 각 인자를 끝으로 하는 범위를 반환
```kotlin
val numbers = 1..6
numbers.flatMap { number -> 1..number }
          .forEach{ println(it) }
```

* 도시 이름의 길이가 5이하면 "A"그룹에, 그렇지 않으면 "B"그룹에 대입합니다.  
  여기에서 지정하는 이름은 반환되는 맵의 키값으로 사용
```kotlin
val cities = listOf("Seoul", "Tokyo", "Mountain View")
cities.groupBy { city -> if(city.length <= 5) "A" else "B" }
        .forEach { key, mapCities -> println("key=$key mapCities=$mapCities") }
```

## Filter


###### val cities = listOf("Seoul", "Tokyo", "Mountain View", "NYC", "Singapore")

* 도시 이름의 길이가 5이하인 항목만 통과
```kotlin
cities.filter { city -> city.length <= 5 }
        .forEach { println(it) }
```

* 첫번째 인자로부터 하나의 인자만 포함
```kotlin
cities.take(1)
        .forEach { println(it) }
```

* 마지막 인자로부터 두 개의 인자만 포함
```kotlin
cities.takeLast(2)
        .forEach { println(it) }
```

* 문자열의 길이가 5이하인 조건을 만족할 때까지 해당하는 항목을 반환  
  "NYC"와 "Singapore"도 문자열의 길이가 5이하지만,  
  "Mountain View"가 조건을 만족하지 않으므로 이후의 인자들은 모두 무시
```kotlin
cities.takeWhile { city -> city.length <= 5 }
        .forEach { println(it) }
```

* 뒤에서부터 시작하여, 문자열의 길이가 13미만인 조건을 만족할 때까지 해당하는 항목을 반환  
  컬렉션 내 항목의 순서는 유지
```kotlin
cities.takeLastWhile { city -> city.length < 13 }
        .forEach { println(it) }
```

* 첫 번째 인자로부터 하나의 인자를 제외 한다.
```kotlin
cities.drop(1)
        .forEach { println(it)}
```

* 마지막 인자로부터 두 개의 인자를 제외한다.
```kotlin
cities.dropLast(2)
        .forEach { println(it) }
```

* 문자열의 길이가 5이하인 조건을 만족할 때까지 해당하는 항목을 제외  
  "NYC"와 "Singapore"도 문자열의 길이가 5 이하이지만,  
  "Mountain View"가 조건을 만족하지 않으므로 이후의 인자들은 모두 무시
```kotlin
cities.dropWhile { city -> city.length <= 5 }
        .forEach { println(it) }
```

* 뒤에서부터 시작하여, 문자열의 길이가 13 미만인 조건을 만족할 때까지 해당하는 항목을 제외  
  컬렉션 내 항목의 순서는 유지
```kotlin
cities.dropLastWhile { city -> city.length <13 }
        .forEach { println(it) }
```

* 첫 번째 인자를 반환
```kotlin
println(cities.first())
```

* 마지막 인자를 반환
```kotlin
println(cities.last())
```

* 문자열 길이가 5 이상인 첫 번째 인자를 반환
```kotlin
println(cities.first { city -> city.length > 5 })
```

* 문자열 길이가 5 이상인 마지막 인자를 반환
```kotlin
println(cities.last { city -> city.length > 5 })
```

* 조건을 만족하는 첫 번째 인자를 반환하며, 없을 경우 예외를 발생
```kotlin
try {
    cities.first { city -> city.isEmpty() }
} catch (e: NoSuchElementException) {
    println("Not found")
}
```

* 조건을 만족하는 마지막 인자를 반환하며, 없을 경우 예외를 발생
```kotlin
try {
    cities.last { city ->city.isEmpty() }
} catch (e: NoSuchElementException) {
    println("Not found")
}
```

* 조건을 만족하는 첫 번째 인자를 반환하며, 없을 경우 널 값을 반환
```kotlin
println(cities.firstOrNull { city -> city.isEmpty() })
```

* 조건을 만족하는 마지막 인자를 반환하며, 없을 경우 널 값을 반환
```kotlin
println(cities.lastOrNull { city -> city.isEmpty() })
```

* 도시 목록 중 중복된 항목을 제거
```kotlin
val cities = listOf("Seoul", "Tokyo", "Mountain View", "Seoul", "Tokyo")
cities.distinct()
        .forEach { println(it) }
```

* 중복된 항목을 판단할 때, 도시 이름의 길이를 판단 기준으로 사용
```
val cities = listOf("Seoul", "Tokyo", "Mountain View", "Seoul", "Tokyo")
cities.distinctBy { city -> city.length }
        .forEach { println(it) }
```