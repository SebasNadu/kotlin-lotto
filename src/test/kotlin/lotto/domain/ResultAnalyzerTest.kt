package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ResultAnalyzerTest {
    @Test
    fun `return rate should be 0 when no winnings`() {
        val rate = ResultAnalyzer.calculateReturnRate(
            totalAmount = 10_000,
            results = List(5) { Rank.MISS }
        )
        assertThat(rate).isEqualTo(0.0)
    }

    @Test
    fun `return rate should be 100 percent when winnings equal investment`() {
        val rate = ResultAnalyzer.calculateReturnRate(
            totalAmount = 10_000,
            results = listOf(
                Rank.FOURTH,
                Rank.MISS,
                Rank.MISS,
                Rank.MISS,
                Rank.MISS
            )
        )
        assertThat(rate).isEqualTo(500.0)
    }

    @Test
    fun `return rate should handle winnings over total amount`() {
        val rate = ResultAnalyzer.calculateReturnRate(
            totalAmount = 10_000,
            results = listOf(Rank.THIRD)
        )
        assertThat(rate).isEqualTo(15000.0)
    }

    @Test
    fun `return rate should handle mixed winnings`() {
        val rate = ResultAnalyzer.calculateReturnRate(
            totalAmount = 10_000,
            results = listOf(
                Rank.FIFTH,
                Rank.FOURTH,
                Rank.MISS
            )
        )
        assertThat(rate).isEqualTo(550.0)
    }
}
