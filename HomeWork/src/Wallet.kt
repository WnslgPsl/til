fun Money.exchangeMoney(): Int {
    return when (this) {
        is MyDallar -> amount * 1000
        is MyKrw -> amount/1000
        else -> 0
    }
}

val Int.Won: MyKrw
    get() = MyKrw(this)

val Int.Dallar: MyDallar
    get() = MyDallar(this)

class MyKrw(var amount: Int) : Krw {
    override fun equals(k: Money): Boolean {
        return when (k) {
            is MyDallar -> this.amount == k.exchangeMoney()
            is MyKrw -> this.amount == k.amount
            else -> false
        }
    }

    override fun plus(k: Money): Krw {
        return when(k) {
            is MyDallar -> MyKrw(this.amount + k.exchangeMoney())
            is MyKrw -> MyKrw(this.amount + k.amount)
            else -> throw IndexOutOfBoundsException()
        }
    }
}

class MyDallar(val amount: Int) : Dallar {
    override fun equals(d: Money): Boolean {
        return when (d) {
            is MyDallar -> this.amount == d.amount
            is MyKrw -> this.amount == d.exchangeMoney()
            else -> false
        }
    }
    override fun plus(d: Money): Dallar {
        return when(d) {
            is MyDallar -> MyDallar(this.amount + d.amount)
            is MyKrw -> MyDallar(this.amount + d.exchangeMoney())
            else -> MyDallar(0)
        }
    }
}