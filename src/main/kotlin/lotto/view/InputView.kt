package lotto.view

import lotto.exceptions.LottoException.*

object InputView {
    private const val PURCHASE_PROMPT = "Please enter the purchase amount."
    private const val MANUAL_TICKETS_NUMBER_PROMPT = "\nEnter the number of manual tickets to purchase."
    private const val MANUAL_TICKETS_PROMPT = "\nEnter the numbers for manual tickets."
    private const val WINNING_NUMBERS_PROMPT = "\nPlease enter last week’s winning numbers."
    private const val BONUS_NUMBER_PROMPT = "\nPlease enter the bonus number."

    fun getPurchaseAmount(): Int {
        println(PURCHASE_PROMPT)
        val input = readln()
        return input.trim().toIntOrNull() ?: throw InvalidAmountFormatException(input)
    }

    fun getManualTicketNumber(): Int {
        println(MANUAL_TICKETS_NUMBER_PROMPT)
        val input = readln()
        return input.trim().toIntOrNull() ?: throw InvalidManualTicketsSizeFormatException(input)
    }

    fun printGetManualTicketsHeader() {
        println(MANUAL_TICKETS_PROMPT)
    }

    fun getManualTicket(): Set<Int> {
        val input = readln()
        return parseCommaSeparatedInts(input)
    }

    fun getWinningNumbers(): Set<Int> {
        println(WINNING_NUMBERS_PROMPT)
        val input = readln()
        return parseCommaSeparatedInts(input)
    }

    fun getBonusNumber(): Int {
        println(BONUS_NUMBER_PROMPT)
        val input = readln()
        return input.trim().toIntOrNull() ?: throw InvalidBonusNumberFormatException(input)
    }

    private fun parseCommaSeparatedInts(input: String): Set<Int> =
        input.trim().split(',')
            .map { raw ->
                raw.trim().toIntOrNull()
                    ?: throw InvalidNumbersFormatException(input)
            }.toSet()
}
