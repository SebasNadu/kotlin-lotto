package domain

class Lotto(val numbers: List<Int> = emptyList<Int>()) {
    init {
        require(numbers.size == MIN_SIZE)
        require(numbers.distinct().size == MIN_SIZE)
        require(numbers.all { it in MIN_RANGE..MAX_RANGE })
    }

    override fun toString(): String {
        return numbers.joinToString(", ", "[", "]")
    }

    companion object {
        const val MIN_SIZE = 6
        const val MIN_RANGE = 1
        const val MAX_RANGE = 45
        const val PRICE_OF_TICKET = 1000
        const val CURRENCY = "KRW"
    }
}