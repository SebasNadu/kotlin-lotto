package lotto.domain

import lotto.exceptions.LottoException.InvalidLottoNumbersException

/***
 * This Class represents a lotto ticket,
 *  - contains 6 unique numbers
 *  - in range 1 to 45
 */
class Lotto(val numbers: Set<LottoNumber>) {
    init {
        require(numbers.size == LOTTO_PICK_SIZE) { throw InvalidLottoNumbersException() }
    }

    override fun toString(): String {
        return numbers.joinToString(", ", "[", "]")
    }

    companion object {
        const val LOTTO_PICK_SIZE = 6
    }
}
