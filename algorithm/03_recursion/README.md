## 재귀

</br>

```python
def countdown(i):

    print i

    if i <= 1:
        # 기본단계
        return
    else:
        # 재귀단계
        countdown(i - 1)

print(countdown(5))

실행결과
5
4
3
2
1
None
```

</br>

```python
def fact(x):

    if x == 1:
        # 기본단계
        return 1
    else:
        # 재귀단계
        return x * fact(x-1)

print(fact(5))

실행결과 120
```

</br>

### 이장에서 배운 내용

* 재귀는 함수가 스스로 호출하는 것을 의미
* 모든 재귀함수는 기본단계(재귀를 빠져 나오는 곳)와 재귀 단계(함수가 자기 자신을 호출하는 부분)라는 </br>두 부분으로 나누어져 있습니다.
* 스택에는 푸시(push)와 팝(pop)이라는 두 가지 연산이 있습니다.
    * 자기자신의 함수를 호출 하지만 서로 다른 함수 호출에 대한 x값에 접근할 수 없습니다.
* 모든 함수 호출은 호출 스택을 사용합니다.
* 호출 스택은 너무 커져서 메모리를 엄청나게 소비할 수도 있습니다.
    * 스택을 사용하면 편리하지만 모든 정보를 저장해야 하므로 메모리를 많이 소비합니다.
    * 함수 호출을 할 때마다 메모리를 사용하게 됩니다.
        * 재귀 대신 반복문을 써서 코드를 다시 작성
        * 꼬리 재귀라는 방법을 사용할 수 있습니다.


