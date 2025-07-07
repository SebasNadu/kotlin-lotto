package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ResultAnalyzerTest {
    @Test
    fun `return rate should be 0 when no winnings`() {
        val session = PurchaseSession(amount = 10_000, ticketsRank = List(5) { Rank.MISS })
        val rate = ResultAnalyzer.calculateReturnRate(session)

        assertThat(rate).isEqualTo(0.0)
    }

    @Test
    fun `return rate should be 100 percent when winnings equal investment`() {
        val ticketsRank = listOf(Rank.FOURTH, Rank.MISS, Rank.MISS, Rank.MISS, Rank.MISS)
        val session = PurchaseSession(amount = 10_000, ticketsRank = ticketsRank)
        val rate = ResultAnalyzer.calculateReturnRate(session)

        assertThat(rate).isEqualTo(500.0)
    }

    @Test
    fun `return rate should handle winnings over total amount`() {
        val session = PurchaseSession(amount = 10_000, ticketsRank = listOf(Rank.THIRD))
        val rate = ResultAnalyzer.calculateReturnRate(session)

        assertThat(rate).isEqualTo(15000.0)
    }

    @Test
    fun `return rate should handle mixed winnings`() {
        val session =
            PurchaseSession(
                amount = 10_000,
                ticketsRank =
                    listOf(
                        Rank.FIFTH,
                        Rank.FOURTH,
                        Rank.MISS,
                    ),
            )
        val rate = ResultAnalyzer.calculateReturnRate(session)
        assertThat(rate).isEqualTo(550.0)
    }

    @Test
    fun `evaluateTickets should return correct ranks for full match`() {
        val winning = Lotto((1..6).map { LottoNumber.from(it) }.toSet())
        val tickets =
            listOf(
                Lotto((1..6).map { LottoNumber.from(it) }.toSet()),
            )
        val winningCombination = WinningCombination(winning, LottoNumber.from(7))
        val session = PurchaseSession(winningCombination = winningCombination, automaticTickets = tickets)
        val ranks = ResultAnalyzer.evaluateTickets(session)

        assertThat(ranks).containsExactly(Rank.FIRST)
    }

    @Test
    fun `evaluateTickets should return SECOND rank with 5 match plus bonus`() {
        val winning = Lotto((1..6).map { LottoNumber.from(it) }.toSet())
        val tickets =
            listOf(
                Lotto((1..5).map { LottoNumber.from(it) }.toSet() + LottoNumber.from(7)),
            )
        val winningCombination = WinningCombination(winning, LottoNumber.from(7))
        val session = PurchaseSession(winningCombination = winningCombination, automaticTickets = tickets)
        val ranks = ResultAnalyzer.evaluateTickets(session)

        assertThat(ranks).containsExactly(Rank.SECOND)
    }
}
