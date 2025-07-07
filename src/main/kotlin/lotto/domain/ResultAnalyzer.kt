package lotto.domain

object ResultAnalyzer {
    fun evaluateTickets(
        tickets: List<Lotto>,
        winningCombination: WinningCombination,
    ): List<Rank> = tickets.map { it.getRank(winningCombination) }

    fun calculateReturnRate(
        totalAmount: Int,
        results: List<Rank>,
    ): Double {
        val totalPrize = results.sumOf { it.winningMoney }
        return if (totalPrize == 0) 0.0 else (totalPrize.toDouble() / totalAmount) * 100.0
    }
}
