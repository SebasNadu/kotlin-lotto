package domain

class Lotto(val numbers: List<Int> = emptyList<Int>()) {
    init {
        if (numbers.size != 6)
            throw IllegalArgumentException("Ups")
    }
}