interface Dallar : Money {
    fun equals(d: Money): Boolean
    operator fun plus(d: Money): Dallar
}

interface Krw : Money {

    fun equals(k: Money): Boolean
    operator fun plus(k: Money): Krw
}

interface Money

interface SomethingElse