# Collection 예제

* 특정 타입을 갖는 빈 배열 반환
```
val emptyStringArray = emptyArray<String>()
```

* 특정 타입을 갖는 빈 배열 반환
```
val emptyStringArray = emptyArray<String>()
```

* 배열 내 각 값들이 null값으로 초기화 되고 인자로 받은 size만큼(3)의 크기를 갖는 배열 반환
```
val nullStoreableArray = arrayOfNulls<String>(3)
```

* 인자로 받는 값을 포함하는 읽기 전용 리스트 반환
```
val cities = listOf<String>("Seoul", "Tokyo", "San Francisco")
```

* 비어있는 읽기 전용 리스트 반환
```
val emptyList = listOf<String>()
```

* 인자로 받는 값 중, null 값은 무시하고 아닌 값으로만 리스트를 구성
```
val notNullList = listOfNotNull("1", "2", null, "4")
```

* 인자로 받은 값을 포함하며 수정 가능한 리스트를 반환
```
val mCities = mutableListOf<String>("Seoul", "Tokyo", "San Francisco")
mCities.set(0, "Changwon")
mCities.add(0, "Masan")
```

* 앱 개발 시 자주 사용하는 자료구조 중 하나인 ArrayList 또한 표준 라이브러리 제공
```
val arrayList = arrayListOf<String>("Seoul", "Tokyo", "San Francisco")
arrayList.set(0, "Masan")
arrayList.add(0, "Changwon")
```

* 읽기 전용 맵을 생성하는 함수 정의
  * map1 - Pair를 직접 사용한 예
  * map2 - 표준 라이브러리 내 함수인 to()를 사용하여 직관적으로 표현 할 수 있다.
```
val map1 = mapOf(Pair("SEO", "Seoul"), Pair("TOK", "Tokyo"))
val map2 = mapOf("SEO" to  "Seoul", "TOK" to  "Tokyo")
```
