package lotto.domain

import lotto.exceptions.LottoException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PurchaseSessionTest {
    @Test
    fun `should return a deep copy`() {
        val session1 = PurchaseSession(amount = 1)
        val session2 = session1.copy(manualTicketsNumber = 10)
        assertThat(session2).isNotEqualTo(session1)
        assertThat(session2.amount).isEqualTo(session1.amount)
    }

    @Test
    fun `should update amount and return a new instance from the session`() {
        val session1 = PurchaseSession()
        val session2 = session1.updateAmount(45000)
        assertThat(session2).isNotEqualTo(session1)
        assertThat(session2.amount).isNotEqualTo(session1.amount)
    }

    @Test
    fun `should throw an exception and not update amount due a invalid amount`() {
        val session1 = PurchaseSession()
        assertThrows<LottoException> { session1.updateAmount(45100) }
    }

    @Test
    fun `should update manual tickets number and return a new instance from the session`() {
        val session1 = PurchaseSession(allTicketsNumber = 10)
        val session2 = session1.updateManualTicketsNumber(1)
        assertThat(session2).isNotEqualTo(session1)
        assertThat(session2.manualTicketsNumber).isNotEqualTo(session1.manualTicketsNumber)
    }

    @Test
    fun `should throw an exception and not update manual tickets number due a invalid amount`() {
        val session1 = PurchaseSession(allTicketsNumber = 10)
        assertThrows<LottoException> { session1.updateManualTicketsNumber(11) }
    }

    @Test
    fun `should update manual tickets and return a new instance from the session`() {
        val ticketsSize = 5
        val session1 = PurchaseSession(manualTicketsNumber = 5)
        val session2 = session1.updateManualTickets(List(ticketsSize) { Lotto.fromInts(setOf(1, 2, 3, 4, 5, 6)) })
        assertThat(session2).isNotEqualTo(session1)
        assertThat(session2.manualTickets.size).isEqualTo(ticketsSize)
    }

    @Test
    fun `should throw an exception and not update manual tickets due a invalid amount`() {
        val ticketsSize = 5
        val session1 = PurchaseSession(manualTicketsNumber = 6)
        assertThrows<LottoException> {
            session1.updateManualTickets(
                List(ticketsSize) {
                    Lotto.fromInts(
                        setOf(
                            1,
                            2,
                            3,
                            4,
                            5,
                            6,
                        ),
                    )
                },
            )
        }
    }

    @Test
    fun `should update automatic tickets and return a new instance from the session`() {
        val ticketsSize = 5
        val session1 = PurchaseSession(allTicketsNumber = 5)
        val session2 = session1.updateAutomaticTickets(List(ticketsSize) { Lotto.fromInts(setOf(1, 2, 3, 4, 5, 6)) })
        assertThat(session2).isNotEqualTo(session1)
        assertThat(session2.automaticTickets.size).isEqualTo(ticketsSize)
    }

    @Test
    fun `should throw an exception and not update automatic tickets due a invalid amount`() {
        val ticketsSize = 5
        val session1 = PurchaseSession(allTicketsNumber = 6)
        assertThrows<LottoException> {
            session1.updateAutomaticTickets(
                List(ticketsSize) {
                    Lotto.fromInts(
                        setOf(
                            1,
                            2,
                            3,
                            4,
                            5,
                            6,
                        ),
                    )
                },
            )
        }
    }
}
