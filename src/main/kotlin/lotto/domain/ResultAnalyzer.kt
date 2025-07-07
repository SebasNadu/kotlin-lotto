package lotto.domain

import lotto.exceptions.LottoException.InvalidTicketsEvaluation

object ResultAnalyzer {
    fun evaluateTickets(
        session: PurchaseSession
    ): List<Rank> =
        session.allTickets.map {
            it.getRank(session.winningCombination ?: throw InvalidTicketsEvaluation())
        }

    fun calculateReturnRate(
        session: PurchaseSession
    ): Double {
        val totalPrize = session.ticketsRank.sumOf { it.winningMoney }
        return if (totalPrize == 0) 0.0 else (totalPrize.toDouble() / session.amount) * 100.0
    }
}
