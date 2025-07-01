package domain

class Lotto(val numbers: List<Int> = emptyList<Int>()) {
    init {
        require(numbers.size == 6)
        require(numbers.distinct().size == 6)
    }
}