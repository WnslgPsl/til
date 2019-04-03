fun main () {

    println(5.Dallar.equals(5000.Won)) // $5는 ￦5,000과 같다.
    println(10.Dallar.equals(10000.Won)) // $10는 ￦10,000과 같다.
    println((5.Dallar + 5.Dallar).equals(10.Dallar)) // $5와 $5를 더한 결과는 $10이다.
    println((5000.Won + 5000.Won).equals(10000.Won)) // ￦5,000와 ￦5,000를 더한 결과는 ￦10,000이다.
    println((5.Dallar + 5000.Won).equals(10.Dallar)) // $5와 ￦5,000을 더한 결과는 $10이다.

}


