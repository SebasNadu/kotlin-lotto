package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RankTest {
    @Test
    fun `Invalid count Match`() {
        assertThat(Rank.valueOf(8)).isEqualTo(Rank.MISS)
    }

    @Test
    fun `Valid count Match`() {
        assertThat(Rank.valueOf(6)).isEqualTo(Rank.FIRST)
    }

    @Test
    fun `Valid count Match with Bonus`() {
        assertThat(Rank.valueOf(5, true)).isEqualTo(Rank.SECOND)
    }

    @Test
    fun `Valid count Match without Bonus`() {
        assertThat(Rank.valueOf(5, false)).isEqualTo(Rank.THIRD)
    }
}
