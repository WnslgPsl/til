# Observable 클래스

> 사용자가 버튼을 누르면 버튼에 미리 등록해둔 onClick() 메서드를 호출해  
원하는 처리를 하는 것이 옵서버 패턴의 대표적인 예  

> RxJava의 Observable은 세 가지의 알림을 구독자에게 전달한다.
> * **onNext** : Observable이 데이터의 발행을 알린다. 기존의 옵서버 패턴과 같다.
> * **onComplete** : 모든 데이터의 발행을 완료했음을 알린다.  
onComplete 이벤트는 단 한 번만 발생하며, 발생한 후에는 더이상 onNext 이벤트가 발생해선 안 된다.
> * **onError** : Observable에서 어떤 이유로 에러가 발생했음을 알린다.  
이벤트가 발생하고 나면 onNext 및 onComplete 이벤트가 발생하지 않는다.  
즉 Observable의 실행을 종료한다.  


## just() 함수

* just() 함수는 인자로 넣은 데이터를 차례로 발행하려고 Observable을 생성한다.
* 한 개의 값을 넣을 수도 있고 인자로 여러 개의 값(최대 10개)을 넣을 수도 있다.  
(단 타입은 모두 같아야 한다.)
</br>
</br>
![](/assets/just.c.png)

* 원은 Observable에서 발행하는 데이터로 just() 함수를 거치면 입력한 원을 그대로 발행한다.  
파이프( | ) 표시는 모든 데이터 발행이 완료 되었엄을 의미한다.