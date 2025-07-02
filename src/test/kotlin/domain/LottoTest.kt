package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTest {
    @Test
    fun `Illegal Lotto`() {
        assertThrows<IllegalArgumentException> {
            Lotto()
        }
    }

    @Test
    fun `Lotto has 6 numbers`() {
        val ticket = listOf(1, 2, 3, 4, 5, 6)
        assertThat(Lotto(ticket))
    }

    @Test
    fun `Lotto has 6 not unique numbers`() {
        val ticket = listOf(1, 1, 2, 3, 4, 5)
        assertThrows<IllegalArgumentException> {
            Lotto(ticket)
        }
    }

    @Test
    fun `Lotto numbers are in range`() {
        val ticket = listOf(1, 2, 3, 4, 5, 45)
        assertThat(Lotto(ticket))
    }

    @Test
    fun `Lotto numbers are not in range`() {
        val ticket = listOf(1, 100, 2, 3, 4, 5)
        assertThrows<IllegalArgumentException> {
            Lotto(ticket)
        }
    }

    @Test
    fun `Lotto to string with the correct format`() {
        val ticket = Lotto(listOf(1, 2, 3, 4, 5, 6))
        assertThat(ticket.toString()).isEqualTo("[1, 2, 3, 4, 5, 6]")
    }
}

