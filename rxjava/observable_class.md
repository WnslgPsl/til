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

* **just\(\) 함수의 마블 다이어그램**

![just](/assets/just.c.png)

* 원은 Observable에서 발행하는 데이터로 just\(\) 함수를 거치면 입력한 원을 그대로 발행한다.  
  파이프\( \| \) 표시는 모든 데이터 발행이 완료 되었엄을 의미한다.

```Java
    public void emit() {
        Observable.just(1, 2, 3, 4, 5, 6)
        .subscribe(System.out::println);    
    }

    결과값
    1
    2
    3
    4
    5
    6
```



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
```









