package services

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
class LottoServiceTest {


    @ValueSource(ints = [TICKET_PRICE, TICKET_PRICE * 2, TICKET_PRICE * 3 ])
    @ParameterizedTest
    fun `return true if ticket price is valid`(ticketPrice: Int) {
        assertThat(
            LottoService.purchaseAmountValidator(ticketPrice)
        ).isEqualTo(true)
    }

    @ValueSource(ints = [TICKET_PRICE - 1, TICKET_PRICE + 1, TICKET_PRICE * 2 + 1 ])
    @ParameterizedTest
    fun `return false if ticket price is invalid`(ticketPrice: Int) {
        assertThat(
            LottoService.purchaseAmountValidator(ticketPrice)
        ).isEqualTo(false)
    }

    @Test
    fun `generate valid lotto tickets list`() {
        assertThat(LottoService.generateLottoTickets(TICKET_PRICE * 2).size)
            .isEqualTo(TICKET_PRICE * 2 / TICKET_PRICE)
    }


    companion object {
        private const val TICKET_PRICE = 1000
    }
}