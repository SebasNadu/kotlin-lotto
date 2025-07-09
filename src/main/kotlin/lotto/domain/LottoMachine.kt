package lotto.domain

class LottoMachine {
    fun generateAutomaticTickets(session: PurchaseSession): PurchaseSession {
        return session.updateAutomaticTickets(
            automaticTickets =
                List(session.automaticTicketsNumber) { generateTicket() },
        )
    }

    private fun generateTicket(): Lotto {
        val numbers = NUMBER_POOL.shuffled().take(Lotto.LOTTO_PICK_SIZE).sorted().toSet()
        return Lotto(numbers)
    }

    companion object {
        const val PRICE_OF_TICKET = 1_000
        const val MAX_AMOUNT_ACCEPTED = 100_000
        const val CURRENCY = "KRW"
        private val NUMBER_POOL = (LottoNumber.MINIMUM_NUMBER..LottoNumber.MAXIMUM_NUMBER).map(LottoNumber::from)
    }
}
