package lotto.services

import lotto.domain.Lotto
import lotto.domain.Rank
import lotto.domain.WinningCombination

object LottoService {
    private const val ZERO_DOUBLE = 0.0

    fun getRankFrom(
        ticket: Lotto,
        winningNumbers: WinningCombination,
    ): Rank {
        val countOfMatch = ticket.numbers.count { it in winningNumbers.winningTicket.numbers }
        val matchBonus = ticket.numbers.any { it == winningNumbers.bonusNumber }
        return Rank.valueOf(countOfMatch, matchBonus)
    }

    fun calculateReturnRate(
        totalAmount: Int,
        results: List<Rank>,
    ): Double {
        val totalPrize = results.sumOf { it.winningMoney }
        return if (totalPrize == 0) ZERO_DOUBLE else (totalPrize.toDouble() / totalAmount) * 100.0
    }

}
