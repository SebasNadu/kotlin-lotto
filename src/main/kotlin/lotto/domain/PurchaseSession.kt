package lotto.domain

import lotto.exceptions.LottoException.InvalidAmountException
import lotto.exceptions.LottoException.InvalidAutomaticTicketsException
import lotto.exceptions.LottoException.InvalidManualTicketsException
import lotto.exceptions.LottoException.InvalidManualTicketsNumberException

data class PurchaseSession(
    val amount: Int = 0,
    val allTicketsNumber: Int = 0,
    val manualTicketsNumber: Int = 0,
    val automaticTickets: List<Lotto> = emptyList(),
    val manualTickets: List<Lotto> = emptyList(),
    val winningCombination: WinningCombination? = null,
    val ticketsRank: List<Rank> = emptyList(),
    val returnRate: Double = 0.0,
) {
    val automaticTicketsNumber: Int
        get() = allTicketsNumber - manualTicketsNumber
    val allTickets: List<Lotto>
        get() = manualTickets + automaticTickets

    fun updateAmount(amount: Int): PurchaseSession {
        val condition = amount >= LottoMachine.PRICE_OF_TICKET && amount % LottoMachine.PRICE_OF_TICKET == 0
        require(condition && amount <= LottoMachine.MAX_AMOUNT_ACCEPTED) {
            throw InvalidAmountException(amount)
        }
        return copy(amount = amount, allTicketsNumber = amount / LottoMachine.PRICE_OF_TICKET)
    }

    fun updateManualTicketsNumber(count: Int): PurchaseSession {
        require(count <= allTicketsNumber) {
            throw InvalidManualTicketsNumberException(count)
        }
        return copy(manualTicketsNumber = count)
    }

    fun updateManualTickets(manualTickets: List<Lotto>): PurchaseSession {
        require(manualTickets.size == manualTicketsNumber) {
            throw InvalidManualTicketsException()
        }
        return copy(manualTickets = manualTickets)
    }

    fun updateAutomaticTickets(automaticTickets: List<Lotto>): PurchaseSession {
        require(automaticTickets.size == automaticTicketsNumber) {
            throw InvalidAutomaticTicketsException()
        }
        return copy(automaticTickets = automaticTickets)
    }

    fun updateWinningCombination(winningCombination: WinningCombination) = copy(winningCombination = winningCombination)

    fun updateTicketsRank(ticketsRank: List<Rank>): PurchaseSession = copy(ticketsRank = ticketsRank)

    fun updateReturnRate(returnRate: Double): PurchaseSession = copy(returnRate = returnRate)
}
