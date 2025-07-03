package lotto.domain

import lotto.exceptions.LottoException
import lotto.util.LottoUtils

/***
 * This Class represents a lotto ticket,
 *  - contains 6 unique numbers
 *  - in range 1 to 45
 */
class Lotto(val numbers: List<Int> = emptyList<Int>()) {
	init {
		LottoUtils.requireOrThrow(
			numbers.size == LOTTO_PICK_SIZE,
			LottoException.InvalidWinningNumbersException(numbers)
		)
		LottoUtils.requireOrThrow(
			numbers.distinct().size == LOTTO_PICK_SIZE,
			LottoException.InvalidWinningNumbersException(numbers)
		)
		LottoUtils.requireOrThrow(
			numbers.all { LottoUtils.isInRange(it) },
			LottoException.InvalidWinningNumbersException(numbers)
		)
	}

	override fun toString(): String {
		return numbers.joinToString(", ", "[", "]")
	}

	companion object {
		const val LOTTO_PICK_SIZE = 6
		const val MIN_RANGE = 1
		const val MAX_RANGE = 45
		const val PRICE_OF_TICKET = 1000
		const val CURRENCY = "KRW"
	}
}
