package lotto.view

import lotto.exceptions.LottoException

object InputView {
	fun getPurchaseAmount(): Int {
		println(Constants.PURCHASE_PROMPT)
		val input = readln()
		return input.trim().toIntOrNull() ?: throw LottoException.InvalidAmountFormatException(input)
	}

	fun getWinningNumbers(): List<Int> {
		println(Constants.WINNING_NUMBERS_PROMPT)
		val input = readln()
		return input
			.split(',')
			.map {
				it.trim().toIntOrNull()
					?: throw LottoException.InvalidWinningNumbersFormatException(input)
			}
	}

	fun getBonusNumber(): Int {
		println(Constants.BONUS_NUMBER_PROMPT)
		val input = readln()
		return input.trim().toIntOrNull() ?: throw LottoException.InvalidBonusNumberFormatException(input)
	}

	object Constants {
		const val PURCHASE_PROMPT = "Please enter the purchase amount."
		const val WINNING_NUMBERS_PROMPT = "Please enter last week’s winning numbers."
		const val BONUS_NUMBER_PROMPT = "Please enter the bonus number."
	}
}