package lotto.domain

import lotto.exceptions.LottoException.InvalidAmountException

class LottoMachine {
    fun purchase(amount: Int): List<Lotto> {
        validatePurchase(amount)
        val numberOfTickets = amount / PRICE_OF_TICKET
        return List(numberOfTickets) { generateTicket() }
    }

    fun validatePurchase(amount: Int) {
        require(amount >= PRICE_OF_TICKET && amount % PRICE_OF_TICKET == 0) {
            throw InvalidAmountException(amount)
        }
    }

    private fun generateTicket(): Lotto {
        val numbers = NUMBER_POOL.shuffled().take(Lotto.LOTTO_PICK_SIZE).sorted().toSet()
        return Lotto(numbers)
    }

    companion object {
        const val PRICE_OF_TICKET = 1_000
        const val CURRENCY = "KRW"
        private val NUMBER_POOL = (LottoNumber.MINIMUM_NUMBER..LottoNumber.MAXIMUM_NUMBER).map(LottoNumber::from)
    }
}
