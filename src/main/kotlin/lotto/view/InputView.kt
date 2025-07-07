package lotto.view

import lotto.exceptions.LottoException

object InputView {
    private const val PURCHASE_PROMPT = "Please enter the purchase amount."
    private const val WINNING_NUMBERS_PROMPT = "Please enter last week’s winning numbers."
    private const val BONUS_NUMBER_PROMPT = "Please enter the bonus number."

    fun getPurchaseAmount(): Int {
        println(PURCHASE_PROMPT)
        val input = readln()
        return input.trim().toIntOrNull() ?: throw LottoException.InvalidAmountFormatException(input)
    }

    fun getWinningNumbers(): Set<Int> {
        println(WINNING_NUMBERS_PROMPT)
        val input = readln()
        return parseCommaSeparatedInts(input)
    }

    fun getBonusNumber(): Int {
        println(BONUS_NUMBER_PROMPT)
        val input = readln()
        return input.trim().toIntOrNull() ?: throw LottoException.InvalidBonusNumberFormatException(input)
    }

    private fun parseCommaSeparatedInts(input: String): Set<Int> =
        input.trim().split(',')
            .map { raw ->
                raw.trim().toIntOrNull()
                    ?: throw LottoException.InvalidWinningNumbersFormatException(input)
            }.toSet()
}
