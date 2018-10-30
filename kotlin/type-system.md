스터디 발표를 위해 준비중입니다.

# 코틀린 타입 시스템

## 6.1 널 가능성

### 6.1.1 null이 될수 있는 type

* 코틀린은 null이 될수 있는 타입을 명시적으로 표시할 수 있습니다.

```java
// 자바
public int getLen(String str) {
    return str.lengh();
}

// 컴파일시에는 문제가 없지만 런타임에서 NPE발생
getlen(null);
```

```kotlin
fun getlen(str: String) = str.length

// 컴파일단에서 null이 들어갈 수 없음을 알려준다.
getLen(null) // 에러
```

* Type에 ?를 붙여주면 null이 가능한 프로퍼티임을 명시적으로 표편합니다.

```kotlin
fun strLenSafe(s: String?): Int =
    if (s != null) s.length else 0 
    // null체크없이 s.length를 호출하면 역시 컴파일 에러 발생 

fun main(args: Array<String>) {
    val x: String? = null
    println(strLenSafe(x))
    println(strLenSafe("abc"))
}
```

### 6.1.3 null safe operator

* Kotlin은 null을 안전하게 처리하기위해 ?. 연산자를 제공합니다.

```kotlin
fun printAllCaps(s: String?) {
    val allCaps: String? = s?.toUpperCase()
    println(allCaps)
}

fun main(args: Array<String>) {
    printAllCaps("abc")
    printAllCaps(null)
}
```

* if(s != null) s.toUIpperCase() else null 와 같습니다.
* null이 입력되면 null을 반환합니다.

* property에도 ?.으 사용하여 편리하게 null을 처리할 수 있습니다.
```kotlin
class Employee(val name: String, val manager: Employee?)

fun managerName(employee: Employee): String? = employee.manager?.name

fun main(args: Array<String>) {
    val ceo = Employee("Da Boss", null)
    val developer = Employee("Bob Smith", ceo)
    println(managerName(ceo))
    println(managerName(developer))
}
```

* 아래처럼 연속적인 사용도 가능합니다.
```kotlin
class Address(val streetAddress: String, val zipCode: Int,
              val city: String, val country: String)

class Company(val name: String, val address: Address?)

class Person(val name: String, val company: Company?)

fun Person.countryName(): String {
   val country = this.company?.address?.country
   return if (country != null) country else "Unknown"
}

fun main(args: Array<String>) {
    val person = Person("Dmitry", null)
    println(person.countryName())
}
```

### 6.1.4 elvis operator

* ?. 연산자는 null이 입력되면 null을 반환합니다. 필요에 따라서 null일 경우 default값을 지정해야되는 경우도 있습니다. 이때 ?: 사용하여 편리하게 처리할 수 있습니다.

![elvis](/assets/elvis.jpeg)

* 연산자 모양이 엘비스 프레슬리를 닮아서 붙여진 이름이라고 합니다...

```kotlin
fun getName(str: String?) {
    val name = str?:"Unknown"
}
```

* 위 코드는 if (str != null) str else "Unknown" 과 같은 코드 입니다.
* elvis operator 우측에는 return 이나 throw도 넣을 수 있습니다.

```kotlin
class Address(val streetAddress: String, val zipCode: Int,
              val city: String, val country: String)

class Company(val name: String, val address: Address?)

class Person(val name: String, val company: Company?)

fun printShippingLabel(person: Person) {
    val address = person.company?.address
      ?: throw IllegalArgumentException("No address") //company 정보가 없으면 exception 강제 발생
    with (address) {
        println(streetAddress)
        println("$zipCode $city, $country")
    }
}

fun main(args: Array<String>) {
    val address = Address("Elsestr. 47", 80687, "Munich", "Germany")
    val jetbrains = Company("JetBrains", address)
    val person = Person("Dmitry", jetbrains)
    printShippingLabel(person)
    printShippingLabel(Person("Alexey", null))
}
```

* ?.을 연속적으로 사용하고 마지막에 ?: 사용할 경우 어디서 null이 발생했는지 확인하기 어렵습니다. 각 ?. 별로 default 값을 다르게 표기하고 싶으면 ?.을 따로 분리하여 사용해야 합니다.

#### 6.1.5 safe cast

* 스마트 캐스트인 is 를 사용하면 as를 사용하지 않고도 type을 변환할 수 있습니다.
하지만 as를 바로 사용하여 casting 할때 type이 맞지 않으면 ClassCastException이 발생합니다.

* 따라서 이를 방지하기 위해 as? 를 지원합니다. as? 는 casting을 시도하고 불가능하면 null을 반환합니다.

```kotlin
class Person(val firstName: String, val lastName: String) {
    override fun equals(o: Any?): Boolean {
        val otherPerson = o as? Person ?: return false
        // 안전한 casting을 하고나면 otherPerson이 Person 타입으로 스마트 캐스트 된다.
        return otherPerson.firstName == firstName &&
                otherPerson.lastName == lastName
    }

    override fun hashCode(): Int =
            firstName.hashCode() * 37 + lastName.hashCode()
}

fun main(args: Array<String>) {
    val p1 = Person("Dmitry", "Jemerov")
    val p2 = Person("Dmitry", "Jemerov")
    println(p1 == p2)
    println(p1.equals(42))
}
```

### 6.1.6 강제 not null 처리

* property를 nullable로 설정하면 사용할때마다 null처리를 해야합니다. 실제로 절대로 null이 들어갈 수 없는 경우도 있습니다. 하지만 컴파일러는 모르기 때문에 계속 null 처리를 진행 해야합니다.

```kotlin
fun ignoreNulls(s: String?) {
    val sNotNull: String = s!!
    println(sNotNull.length)
}

fun main(args: Array<String>) {
    ignoreNulls(null)
}
```
* !!를 property에 사용하게되면 강제로 null이 아님을 보증하게 됩니다. 따라서 이후에는 property가 not null로 인식하게 됩니다.

* 자바에서 위와같은 코드는 println(sNotNull.length) 라인에서 NPE가 발생합니다.
* kotlin에서는 val sNotNull: String = s!! 라인에서 NPE가 발생하여 좀더 명확한 위치를 잡아줍니다.
> !! 기호는 컴파일러에게 소리를 지르는 것 같은 느낌을 줍니다. 컴파일러가 검증할 수 없는 단언을 사용하기보다는 더 나은 방법을 찾아보라는 뜻으로 의도한 것이라고 합니다.

```kotlin
person.company!!.address!!.country
```

* 어떤값이 null인지 확실히 하기 위해 여러 !! 연산자를 한 줄에 함께 쓰는 일을 피해야 합니다.

### 6.1.7 let 함수

* 널이 될 수 있는 값을 널이 아닌 값만 인자로 받는 함수에 넘기려면 어떻게 해야할까? 그런 호출은 안전하지 않기 때문에 컴파일러는 허용하지 않습니다.
* let 함수를 사용하면 자신의 receiver 객체를 람다식 내부로 넘겨주고 블록의 결과값을 반환합니다.

```kotlin
fun <T, R> T.let(block: (T) -> R): R
```

```kotlin
val padding = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, 16f, resources.displayMetrics).toInt()
// 왼쪽, 오른쪽 padding 설정
setPadding(padding, 0, padding, 0)


TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16f,
        resources.displayMetrics).toInt().let {
    // padding 대신 it 사용
    setPadding(it, 0, it, 0)
}
```
* padding의 값은 한번만 사용되고 더 이상 사용되지 않습나다. 이런경우 let을 사용하면 불필요한 선언을 줄일 수 있습니다.

* 아래와 같이 안전한호출 (?.)과 함께 사용할 수 있습니다.

```kotlin
fun sendEmailTo(email: String) {
    println("Sending email to $email")
}

fun main(args: Array<String>) {
    var email: String? = "yole@example.com"
    email?.let { sendEmailTo(it) }
    email = null
    email?.let { sendEmailTo(it) }
}
```
* null 일때는 아무런 실행을 하지 않습니다.

* 마찬가지로 elvis operator(?:)를 사용해서 null일시 default값을 설정 할 수 있습니다.

* let은 계속하여 중첩사용이 가능하지만 중첩이 늘어나면 코드가 복잡해져서 알아보기 어려워 집니다. 그런 경우 일반적인 if를 사용해서 모든 값을 한꺼번에 검사하는 편이 낫습니다.

### 6.1.8 나중에 초기화할 Property

* 객체 생성후 초기화를 나중에 하는 코드들이 많이 존재합니다. kotlin에서 property 초기화는 생성자 안에서만 가능하기 때문에 나중에 초기화를 해야하는 코드들을 사용하기가 어렵습니다. 특히니 val로 선언된 property는 반드시 생성자 안에서 초기화 해야합니다.

```kotlin
class MyService {
    fun performAction(): String = "foo"
}

class MyTest {
    private lateinit var myService: MyService

    @Before fun setUp() {
        myService = MyService()
    }

    @Test fun testAction() {
        Assert.assertEquals("foo",
            myService.performAction())
    }
}
```

* lateinit으로 myService를 선언했기 때문에 not null이지만 초기화 작업을 바로 하지 않아도 됩니다.

* 지연 초기화하는 property는 항상 var이여야 합니다. val property는 final로 컴파일 되기 때문에 반드시 초기화 되어야 합니다.

* 초기화 전에 Myservice 함수에 접근하면 run time에 "lateinit property myService has not been initialized" 란 exception이 발생할 수 있습니다.

### 6.1.9 널이 될 수 있는 타입확장

* null이 가능한 객체에 확장함수를 선언할 수 있습니다.

```kotlin
fun verifyUserInput(input: String?) {
    if (input.isNullOrBlank()) {
        println("Please fill in the required fields")
    }
}

fun main(args: Array<String>) {
    verifyUserInput(" ")
    verifyUserInput(null)
}
```

* null이 될수 있는 String? type에서는 isNullOrBlank() 또는 isNullOrEmpty() 함수를 지원합니다.

* 보통 자바에서 null.isNullOrBlank()를 호출하면 NPE가 발생합니다.

```kotlin
fun String?.isNullOrBlank(): Boolean =
    this == null || this.isBlank() // 두번째 this에는 스마트 캐스트가 적용됩니다.
```

### 6.1.10 타입 파리미터(Generic)의 널 가능성

* 함수나 클래스의 모든 타입 파라미터는 기본적으로 null이 될수 있습니다.

```kotlin
fun <T> printHashCode(t: T) {
    println(t?.hashCode())
}

fun main(args: Array<String>) {
    printHashCode(null) // T의 타입은 "Any?"로 추론됩니다.
}
```

* T에 ?가 붙지 않았지만 기본적으로 ?가 있습니다.
* 함수 내부에서는 반드시 null 체크를 해야합니다.

```kotlin
fun <T: Any> printHashCode(t: T) {
    println(t.hashCode())
}

fun main(args: Array<String>) {
    printHashCode(null) // 컴파일 에러
}
```

* 타입 파라미터가 null이 아님을 확실히 하려면 null이 될 수 없는 타입 상한(upper bound)를 지정해야 합니다.

### 6.1.11 널 가능성과 자바 (플랫폼 타입)

* 자바 타입시스템은 null 가능성을 지원하지 않습니다.
* 자바의 @Nullable String 은 String?와 같고, 자바의 @NotNull String은 String과 같습니다.
* 자바 대부분?의 코드에는 어노테이션이 없습니다. 이런 경우 코틀린의 플랫폼 타입이 됩니다.

* 플랫폼 타입은 null처리를 해도 되고 안해도 상관없습니다.
* 플랫폼 타입은 자동완성? 에서 String! 형태로 표현횝니다.
![platform_type](/assets/platform_type.png)
```java
/* 자바 */
public class Person {
    private final String mName;
    public Person(String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }
}
```
```kotlin
fun yellAtSafe(person: Person) {
    println((person.name ?: "Anyone").toUpperCase() + "!!!")
}

fun main(args: Array) {
    yellAtSafe(Person(null))
}
```

* 코틀린 컴파일러는 이경우 String타입의 null가능성에 대해 전혀 알지 못합니다.
* Exception피하려면 개발자의 몫...

> #### 코틀린이 왜 플랫폼 타입을 도입했는가?  
> 모든 자바 타입을 null이 될 수 있는 타입으로 다루면 결코 널이 될 수 없는 값에 대해서도 불필요한 null검사가 들어갑니다.  
> 자바 ArrayList<String> 을 코틀린에서 ArrayList<String?> 으로 다루면 원소에 접근할 때마다 null검사를 수행해거나 안전한 캐스트를 수행해야 합니다. 이런 식으로 처리하면 null 안전성으로 얻는 이익보다 검사에 드는 비용이 훨씬 더 커집니다. 그래서 코틀린 설계자들은 자바의 타입을 가져온 경우 개발자들에게 책임을 넘김 .....

```kotlin
// 두 선언은 모두 올바른 선언입니다.
val s: String? = person.name
val s1: String = person.name
```

```java
/* 자바 */
interface StringProcessor {
    void process(String value);
}
```

```kotlin
class StringPrinter : StringProcessor {
    override fun process(Value: String) {
        println(value)
    }
}
class NullableStringPrinter : StringProcessor {
    override fun process(value: String?) {
        if (value != null) {
            println(value)
        }
    }
}
```

* 구현 메소드를 다른 코틀린 코드가 호출할 수 있으므로 컴파일러는 null이 아님을 검사하는 단언문을 만들어줍니다.
* 자바 코드에서 메소드에게 null 값을 넘기면 단언문이 발동돼 예외가 발생합니다.


## 6.2 코틀린의 원시 타입

### 6.2.1 원시 타입: Int, Boolean 등
* 코틀린은 원시타입(primitive type)와 래퍼타입(wrapper type)을 구분하지 않습니다.

```java
int a = 1;
List<Integer> list = new List<>();
```
```kotlin
a : Int = 1
list : List<Int> = listof(1,2,3)
```

* 코틀린은 숫자 타입등 원시 타입의 값에 대해 메소드를 호출할 수 있습니다.

```kotlin
fun showProgress(progress: Int) {
    val percent = progress.coerceIn(0, 100)
    println("We're ${percent}% done!")
}

fun main(args: Array) {
    showProgress(146)
}
```

* 컴파일 시 대부분의 경우 Int 타입은 자바 int 타입으로 컴파일 됩니다.
* 컬렉션과 같은 제네릭 클래스를 사용하는 경우 java.lang.Integer 객체가 들어갑니다.
* Int와 같은 코틀린 타입에는 null참조가 들어갈 수 없기 때문에 자바 원시 타입으로 컴파일할 수 있습니다. 반대로 자바 원시타입은 결코 null이 될 수 없으므로 자바 원시타입을 코틀린에서 사용할 때도(플랫폼 타입이 아니다) 널이 될 수 없는 타입으로 취급할 수 있습니다.

### 6.2.2 널이 될 수 있는 원시 타입: Int?, Boolean? 등

* null이 가능한 코틀린 타입은 자바의 원시타입으로 변경이 불가능합니다. 따라서 래퍼타입으로 변경됩니다. 

```kotlin
data class Person(val name: String, val age: Int? = null) {

    fun isOlderThan(other: Person): Boolean? {
        // null가능성이 있으므로 두 값이 모두 null이 아닌지 검사해야합니다.
        if (age == null || other.age == null)
            return null
        return age > other.age
    }
}

fun main(args: Array<String>) {
    println(Person("Sam", 35).isOlderThan(Person("Amy", 42)))
    println(Person("Sam", 35).isOlderThan(Person("Jane")))
}
```

* age property는 null이 될 수 있으므로 컴파일되면 래퍼타입인 Integer로 변환 됩니다.

### 6.2.3 숫자 변환

* 