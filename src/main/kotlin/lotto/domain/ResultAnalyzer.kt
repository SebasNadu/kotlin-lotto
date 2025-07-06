package lotto.domain

object ResultAnalyzer {
    fun calculateReturnRate(
        totalAmount: Int,
        results: List<Rank>,
    ): Double {
        val totalPrize = results.sumOf { it.winningMoney }
        return if (totalPrize == 0) 0.0 else (totalPrize.toDouble() / totalAmount) * 100.0
    }
}