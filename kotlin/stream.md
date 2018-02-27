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
```kotlin
val cities = listOf("Seoul", "Tokyo", "Mountain View", "Seoul", "Tokyo")
cities.distinctBy { city -> city.length }
.forEach { println(it) }
```

## 조합 및 합계

```kotlin
//도시코드를 담은 리스트
val cityCodes = listOf("SEO", "TOK", "MTV", "NYC")
//도시 이름을 담은 리스트
val cityNames = listOf("Seoul", "Tokyo", "Mountain View")
//도시 리스트
val cities = listOf("Seoul", "Tokyo", "Mountain View", "NYC", "Singapore")
```

* 두 컬렉션 내의 자료들을 조합하여 새로운 자료를 만들 때 사용  
  두 컬렉션 간의 자료의 개수가 달라도 사용할 수 있으며,  
  더 적은 쪽 자료의 수를 따라간다  
```kotlin
//단순히 zip 함수를 호출하는 경우, Pair 형태로 자료를 조합
cityCodes.zip(cityNames)
        .forEach { pair -> println("${pair.first}:${pair.second}") 
}
//조합할 자료의 타입을 조합 함수를 통해 지정하면 해당 형태로 바꿈
cityCodes.zip(cityNames) {code, name -> "$code ($name)"}
        .forEach { println(it) }
```

* 컬렉션 내 자료를 문자열 형태로 변환함과 동시에 이를 조합하여 하나의 문자열로 생성
```kotlin
//기본 설정값을 사용하여 문자열 형태로 조합
println(cities.joinToString())
//구분자로 다른 문자를 사용하도록 조합
println(cities.joinToString(separator = "|"))
```

* 컬렉션 내 포함된 자료의 개수를 반환하며,  
  별도의 조건식을 추가하면 해당 조건을 만족하는 자료의 개수를 반환
```kotlin
//컬렉션 내 포함된 모든 자료의 개수를 반환
println(cities.count())
//컬렉션 내 포함된 자료 중, 길이가 5 이하인 자료의 개수를 반환
println(cities.count { city -> city.length <= 5 })
```

* 컬렉션 내 자료들을 모두 합쳐 하나의 값으로 만들어 주는 역할  
  joinToString() 함수는 reduce() 함수의 일종
```kotlin
//acc에는 지금까지 조합된 결과가, s에는 새로 조합될 자료가 들어감
println(cities.reduce { acc, s -> "$acc, $s" })
//마지막 인자부터 조합
println(cities.reduceRight { s, acc -> "$acc, $s" })
```

* reduce()함수와 거의 동일한 역할을 하나, 초깃값을 지정할 수 있다
```kotlin
//첫번째 인자부터 조합
println(cities.fold("Initial") { acc, s -> "$acc, $s" })
//마지막 인자부터 조합
println(cities.foldRight("Initial") { s, acc -> "$acc, $s" })
```

* any() 및 none()
```kotlin
//cities 리스트 내에 자료가 존재하는지 확인 - true
rintln(cities.any())
//문자열 길이가 5 이하인 자료가 있는지 확인 - true
println(cities.any { city -> city.length <= 5 })
//cities 리스트 내에 자료가 조재하지 않는지 확인 - false
println(cities.none())
//빈 문자열을 가진 자료가 존재하지 않는지 확인 - true
println(cities.none { city -> city.isEmpty() })
```

* max() 및 min() 함수는 숫자 타입의 자료를 갖는 컬렉션 내에서 각각 최댓값 및 최솟값을 찾아 반환
```kotlin
val numbers = listOf(4, 2, 5, 3, 2, 0, 8)
//최댓값을 찾아 반환
println(numbers.max())
//최솟값을 찾아 반환
println(numbers.min())
//컬렉션 내 자료들의 평균을 반환
println(numbers.average())
```