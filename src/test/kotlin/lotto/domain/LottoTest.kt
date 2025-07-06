package lotto.domain

import lotto.exceptions.LottoException.InvalidLottoNumberException
import lotto.exceptions.LottoException.InvalidLottoNumbersException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTest {
    private fun createLotto(vararg numbers: Int): Lotto =
        Lotto(numbers.map(LottoNumber::from).toSet())

    @Test
    fun `should create lotto with valid numbers`() {
        assertThat(createLotto(1, 2, 3, 4, 5, 6)).isInstanceOf(Lotto::class.java)
    }

    @Test
    fun `Lotto numbers are in range`() {
        val lotto = createLotto(1, 45, 2, 3, 4, 5)
        assertThat(lotto.numbers).hasSize(6)
    }

    @Test
    fun `should throw when lotto has not numbers`() {
        assertThrows<InvalidLottoNumbersException> {
            Lotto(emptySet())
        }
    }

    @Test
    fun `should throw when lotto has duplicated numbers`() {
        assertThrows<InvalidLottoNumbersException> {
            createLotto(1, 1, 2, 3, 4, 5)
        }
    }

    @Test
    fun `should throw when lotto has numbers out of range`() {
        assertThrows<InvalidLottoNumberException> {
            createLotto(0, 46, 2, 3, 4, 5)
        }
    }

    @Test
    fun `should format to string correctly`() {
        val ticket = createLotto(1, 2, 3, 4, 5, 6)
        assertThat(ticket.toString()).isEqualTo("[1, 2, 3, 4, 5, 6]")
    }
}
