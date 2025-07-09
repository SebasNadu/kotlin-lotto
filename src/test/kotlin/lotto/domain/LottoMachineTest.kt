package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoMachineTest {
    private val machine = LottoMachine()

    @Test
    fun `should generate correct number of tickets for valid amount`() {
        val numberOfTickets = 10
        var session = PurchaseSession(allTicketsNumber = numberOfTickets)
        session = machine.generateAutomaticTickets(session)
        assertThat(session.automaticTicketsNumber).isEqualTo(numberOfTickets)
        session.automaticTickets.forEach {
            assertThat(it.numbers).hasSize(Lotto.LOTTO_PICK_SIZE)
        }
    }

    @Test
    fun `should generate unique tickets with valid lotto numbers`() {
        val numberOfTickets = 10
        var session = PurchaseSession(allTicketsNumber = numberOfTickets)
        session = machine.generateAutomaticTickets(session)
        assertThat(session.automaticTicketsNumber).isEqualTo(numberOfTickets)
        session.automaticTickets.forEach {
            assertThat(it.numbers).hasSize(Lotto.LOTTO_PICK_SIZE)
            it.numbers.forEach {
                assertThat(it.toInt()).isBetween(1, 45)
            }
        }
    }
}
