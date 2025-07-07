package lotto.view

import lotto.domain.LottoMachine
import lotto.domain.PurchaseSession
import lotto.domain.Rank

object OutputView {
    private const val TITLE_OF_RESULT_PROMPT = "\nWinning Statistics\n------------------"
    private const val TOTAL_RETURN_PROMPT = "Total return rate is"
    private const val TOTAL_RETURN_ENDING_PROMPT = "(A rate below 1 means a loss)"

    fun printTickets(session: PurchaseSession) {
        println(formatTicketHeader(session))
        session.allTickets.forEach(::println)
    }

    fun printResult(session: PurchaseSession) {
        println(TITLE_OF_RESULT_PROMPT)
        Rank.entries.filter { it != Rank.MISS }.reversed().forEach { printResultLine(it, session.ticketsRank) }
        println("$TOTAL_RETURN_PROMPT ${"%.2f".format(session.returnRate)} $TOTAL_RETURN_ENDING_PROMPT")
    }

    fun showErrorMessage(message: String) {
        println(message)
    }

    private fun formatTicketHeader(session: PurchaseSession): String {
        return "\nPurchased ${session.manualTicketsNumber} manual and ${session.automaticTicketsNumber} automatic ${
            pluralizeTicket(session.manualTicketsNumber + session.automaticTicketsNumber)
        }."
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
