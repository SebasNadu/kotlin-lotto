package lotto.util

import lotto.domain.Lotto
import lotto.exceptions.LottoException

object LottoUtils {
	/**
	 * Template function that accept a lambda
	 * returns only in case of successfully
	 */
	fun <T> retryUntilSuccess(
		block: () -> T,
	): T {
		while (true) {
			try {
				return block()
			} catch (e: LottoException) {
				println(e.message)
			}
		}
	}

	/**
	 * @exception LottoException throw only one of LottoException
	 */
	fun requireOrThrow(condition: Boolean, exception: LottoException) {
		if (!condition)
			throw exception
	}

	/**
	 * @return true if the number is in the range 1 to 45
	 */
	fun isInRange(number: Int): Boolean {
		return number in Lotto.MIN_RANGE..Lotto.MAX_RANGE
	}
}

