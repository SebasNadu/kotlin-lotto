package lotto.domain

import lotto.exceptions.LottoException.InvalidLottoNumbersException

/***
 * This Class represents a lotto ticket,
 *  - contains 6 unique numbers
 *  - in range 1 to 45
 */
class Lotto(val numbers: Set<LottoNumber>, val type: LottoType = LottoType.MANUAL) {
    init {
        require(numbers.size == LOTTO_PICK_SIZE) { throw InvalidLottoNumbersException() }
    }

    fun contains(number: LottoNumber): Boolean {
        return numbers.contains(number)
    }

    override fun toString(): String {
        return numbers.joinToString(", ", "[", "]")
    }

    companion object {
        const val LOTTO_PICK_SIZE = 6

        fun fromInts(numbers: Set<Int>, type: LottoType = LottoType.MANUAL): Lotto {
            return Lotto(numbers.map(LottoNumber::from).toSet(), type)
        }
    }
}

/**
 * Determines the rank of a Lotto ticket based on the winning numbers.
 * Use Extension function here to separate the logic of determining rank from the Lotto class itself.
 * This leaves the Lotto class focused on representing a ticket.
 */
fun Lotto.getRank(winningNumbers: WinningCombination): Rank {
    val countOfMatch = this.numbers.count { it in winningNumbers.winningLotto.numbers }
    val matchBonus = this.numbers.any { it == winningNumbers.bonusNumber }
    return Rank.valueOf(countOfMatch, matchBonus)
}
