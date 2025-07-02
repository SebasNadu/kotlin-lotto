package services

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoServiceTest {
    @Test
    fun `amount should equal or bigger than Ticket price`() {
        assertThat(
            LottoService.purchaseAmountValidator(TICKET_PRICE)
        ).isEqualTo(true)
    }

    @Test
    fun `amount should be enough to purchase at least 1 ticket`() {
        assertThat(
            LottoService.purchaseAmountValidator(TICKET_PRICE * 2)
        ).isEqualTo(true)
    }

    @Test
    fun `amount should be divisible for a ticket price`() {
        assertThat(
            LottoService.purchaseAmountValidator(TICKET_PRICE * 2)
        ).isEqualTo(true)
    }

    companion object {
        private const val TICKET_PRICE = 1000
    }
}