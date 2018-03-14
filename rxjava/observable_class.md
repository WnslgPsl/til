# Observable 클래스

> 사용자가 버튼을 누르면 버튼에 미리 등록해둔 onClick\(\) 메서드를 호출해  
> 원하는 처리를 하는 것이 옵서버 패턴의 대표적인 예
>
> RxJava의 Observable은 세 가지의 알림을 구독자에게 전달한다.
>
> * **onNext** : Observable이 데이터의 발행을 알린다. 기존의 옵서버 패턴과 같다.
> * **onComplete** : 모든 데이터의 발행을 완료했음을 알린다.  
>   onComplete 이벤트는 단 한 번만 발생하며, 발생한 후에는 더이상 onNext 이벤트가 발생해선 안 된다.
> * **onError** : Observable에서 어떤 이유로 에러가 발생했음을 알린다.  
>   이벤트가 발생하고 나면 onNext 및 onComplete 이벤트가 발생하지 않는다.  
>   즉 Observable의 실행을 종료한다.

## just\(\) 함수

* just\(\) 함수는 인자로 넣은 데이터를 차례로 발행하려고 Observable을 생성한다.
* 한 개의 값을 넣을 수도 있고 인자로 여러 개의 값\(최대 10개\)을 넣을 수도 있다.  
  \(단 타입은 모두 같아야 한다.\)

* **just\(\) 함수의 마블 다이어그램**![just](/assets/just.c.png)

* 원은 Observable에서 발행하는 데이터로 just\(\) 함수를 거치면 입력한 원을 그대로 발행한다.  
  파이프\( \| \) 표시는 모든 데이터 발행이 완료 되었엄을 의미한다.

```Java
    public void emit() {
        Observable.just(1, 2, 3, 4, 5, 6)
        .subscribe(System.out::println);    
    }

    실행결과
    1
    2
    3
    4
    5
    6
```

---

## subscribe\(\)함수와 Disposable객체

> RxJava는 내가 동작시키기 원하는 것을 사전에 정의해둔 다음 실제 그것이 실행되는 시점을 조절할 수 있다. 이때 사용하는 것이 subscribe\(\) 함수. Observable은 just\(\) 등의 팩토리 함수로 데이터 흐름을 정의한 후 subscribe\(\) 함수를 호해야 실제로 데이터를 발행한다.

* subscribe\(\) 함수는 모두 Disposable 인터페이스의 객체를 리턴한다.

```java
    void dispose()
    booleann isDisposed()
```

* dispose\(\)는 Observable에게 더 이상 데이터를 발행하지 않도록 구독을 해지하는 함수.  
  Observable에 따르면 Observable이 onComplete 알림을 보냈을 때 자동으로 dispose\(\)를 호출해 구독자의 관계를 끊는다.

* 따라서 onComplete 이벤트가 정상적으로 발생했다면 구독자가 별도로 dispose\(\)를 호출할 필요가 없다.

```java
    Observable<Strinng> source = Observable.just("RED", "GREEN", "YELLOW");

    Disposable d = source.subscribe(
        v -> System.out.println("onNext() : value : " + v),
        err -> System.out.println("onError() : err : " + err.getMessage()),
        () -> System.out.println("onComplete()")
    );

    System.out.println("isDisposed() : " + d.isDisposed());

    실행결과
    onNext() : value : RED
    onNext() : value : GREEN
    onNext() : value : YELLOW
    onComplete()
    isDisposed() : true
```

---

## create\(\) 함수

> just\(\) 함수는 데이터를 인자로 넣으면 자동으로 알림 이벤트가 발생하지만 create\(\) 함수는 onNext, onComplete, onError 같은 알림을 개발자가 직접 호출해야 한다. 그래서 create\(\)는 라이브러리가 무언가를 해준다기보다 개발자가 무언가를 직접 하는 느낌이 강한 함수이다.

* **create\(\) 함수의 마블 다이어 그램**![](/assets/create.c.png)

* 구독자에게 데이터를 발행하려면 onNext\(\) 함수를 호출해야 하며 모든 데이터를 발행한 후에는 반드시 onComplete\(\) 함수를 호출해야 한다.

```java
    Observable<Integer> source = Observable.create (
        (ObservableEmitter<Integer> emitter) -> {
            emitter.onNext(100);
            emitter.onNext(200);
            emitter.onNext(300);
            emitter.onComplete();
    });
    source.subscribe(System.out::println);
    // source.subscribe(data -> System.out.println("Result : " + data));
```

* 첫 번째 문장만으로는 실제로 데이터를 발행하지 않고 두 번째 문장에서 subscribe\(\) 함수를 호출했을 때 값을 발행한다. subscribe\(\) 함수를 호출하지 않으면 아무것도 출력되지 않는다는 뜻이다.

* create\(\)는 Rxjava에 익숙한 사용자만 활용하도록 권고한다.

  1. Observable이 구독 해지\(dispose\)되었을 때 등록된 콜백을 모두 해제해야 한다. 그렇지 않으면 잠재적인 메모리 누수가 발생한다.
  2. 구독자가 구독하는 동안에만 onNext와 onComplete 이벤트를 호출해야 한다.
  3. 에러가 발생했을 때는 오직 onError 이벤트로만 에러를 전달해야 한다.
  4. 배압을 직접 처리해야 한다.

---



