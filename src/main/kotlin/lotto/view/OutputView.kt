package lotto.view

import lotto.domain.Lotto
import lotto.domain.LottoMachine
import lotto.domain.Rank

object OutputView {
    private const val TICKETS_NUMBERS_PROMPT = "You have purchased"
    private const val TITLE_OF_RESULT_PROMPT = "Winning Statistics\n------------------"
    private const val TOTAL_RETURN_PROMPT = "Total return rate is"

    fun printTickets(tickets: List<Lotto>) {
        println(formatTicketHeader(tickets.size))
        tickets.forEach(::println)
    }

    fun printResult(
        ranks: List<Rank>,
        returnRate: Double,
    ) {
        println(TITLE_OF_RESULT_PROMPT)
        Rank.entries.filter { it != Rank.MISS }.reversed().forEach { printResultLine(it, ranks) }
        println("$TOTAL_RETURN_PROMPT ${"%.2f".format(returnRate)}")
    }

    fun showErrorMessage(message: String) {
        println("[ERROR]: $message")
    }


    private fun formatTicketHeader(count: Int): String {
        return "$TICKETS_NUMBERS_PROMPT $count ${pluralizeTicket(count)}."
    }

    private fun formatMoney(amount: Int): String = "%,d".format(amount)

    private fun printResultLine(
        entry: Rank,
        results: List<Rank>,
    ) {
        val matchCount = results.count { it.countOfMatch == entry.countOfMatch }
        val hasBonus = if (entry.requiresBonus) " + Bonus Ball" else ""
        val winningMoney = formatMoney(entry.winningMoney)
        val pluralizedTicket = pluralizeTicket(matchCount)
        println("${entry.countOfMatch} Matches$hasBonus ($winningMoney ${LottoMachine.CURRENCY}) - $matchCount $pluralizedTicket")
    }

    private fun pluralizeTicket(size: Int): String {
        return if (size == 1) "ticket" else "tickets"
    }
}
