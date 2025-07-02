package domain

class Lotto(val numbers: List<Int> = emptyList<Int>()) {
    init {
        require(numbers.size == 6)
        require(numbers.distinct().size == 6)
        require(numbers.all { it in 1..45 })
    }

    override fun toString(): String {
        return numbers.joinToString(", ", "[", "]")
    }
}