package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PurchaseSessionTest {

    @Test
    fun `should return a deep copy`() {
        val session1 = PurchaseSession(amount = 1)
        val session2 = session1.copy(manualTicketsSize = 10)
        assertThat(session2).isNotEqualTo(session1)
        assertThat(session2.amount).isEqualTo(session1.amount)
    }
}