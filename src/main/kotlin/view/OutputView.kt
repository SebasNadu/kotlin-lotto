package view

import domain.Lotto
import domain.Rank

object OutputView {
    fun printTickets(tickets: List<Lotto>) {
        val numberOfTickets = tickets.size
        println("${Constants.TICKETS_NUMBERS_PROMPT} " +
                "$numberOfTickets ${pluralizeTicket(numberOfTickets)}.")
        for (ticket in tickets) {
            println(ticket)
        }
    }

    fun printResult(ranks: List<Rank>, returnRate: Double) {
        println(Constants.TITLE_OF_RESULT_PROMPT)
        Rank.entries.filter { it != Rank.MISS }.reversed().forEach { printResultLine(it, ranks) }
        println("${Constants.TOTAL_RETURN_PROMPT} ${"%.2f".format(returnRate)}")
    }

    private fun printResultLine(entry: Rank, results: List<Rank>) {
        val matchCount = results.count { it.countOfMatch == entry.countOfMatch}
        val hasBonus = if (entry.requiresBonus) " + Bonus Ball" else ""
        val winningMoney = "%,d".format(entry.winningMoney)
        println("${entry.countOfMatch} Matches${hasBonus} ($winningMoney ${Lotto.CURRENCY}) - $matchCount tickets")
    }

    /*
    Winning Statistics
    ------------------
    3 Matches (5,000 KRW) - 1 tickets
    4 Matches (50,000 KRW) - 0 tickets
    5 Matches (1,500,000 KRW) - 0 tickets
    5 Matches + Bonus Ball (30,000,000 KRW) - 0 tickets
    6 Matches (2,000,000,000 KRW) - 0 tickets
    Total return rate is 0.35 (A rate below 1 means a loss)
    */

    private fun pluralizeTicket(size: Int): String {
        return if ( size == 1) "ticket" else "tickets"
    }

    object Constants {
        const val TICKETS_NUMBERS_PROMPT = "You have purchased"
        const val TITLE_OF_RESULT_PROMPT = "Winning Statistics\n------------------"
        const val TOTAL_RETURN_PROMPT = "Total return rate is"
    }
}