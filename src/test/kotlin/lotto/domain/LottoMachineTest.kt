package lotto.domain

import lotto.exceptions.LottoException.InvalidAmountException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoMachineTest {
    private val machine = LottoMachine()

    @Test
    fun `should generate correct number of tickets for valid amount`() {
        val amount = 5_000
        val tickets = machine.purchase(amount)
        assertThat(tickets).hasSize(5)
        tickets.forEach {
            assertThat(it.numbers).hasSize(Lotto.LOTTO_PICK_SIZE)
        }
    }

    @Test
    fun `should throw exception for amount less than price`() {
        val amount = 500
        assertThrows<InvalidAmountException> {
            machine.purchase(amount)
        }
    }

    @Test
    fun `should throw exception for amount not divisible by ticket price`() {
        val amount = 2_500 // not divisible by 1,000
        assertThrows<InvalidAmountException> {
            machine.purchase(amount)
        }
    }

    @Test
    fun `should generate unique tickets with valid lotto numbers`() {
        val amount = 10_000
        val tickets = machine.purchase(amount)
        tickets.forEach { ticket ->
            assertThat(ticket.numbers).hasSize(6)
            ticket.numbers.forEach {
                assertThat(it.toInt()).isBetween(1, 45)
            }
        }
    }
}
