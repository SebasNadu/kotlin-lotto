package services

import domain.Lotto
import domain.Rank
import domain.WinningNumbers

object LottoService {
    private const val ZERO = 0.0

    fun purchaseAmountValidator(amount: Int) {
        if (amount < Lotto.PRICE_OF_TICKET || amount % Lotto.PRICE_OF_TICKET != 0)
            throw IllegalArgumentException()
    }

    fun generateLottoTickets(amount: Int): List<Lotto> {
        val numberTickets = amount / Lotto.PRICE_OF_TICKET
        val lottoTickets: MutableList<Lotto> = mutableListOf()
        repeat(numberTickets) {
            val numbers = generateRandomNumbers(Lotto.MIN_SIZE)
            lottoTickets.add(Lotto(numbers))
        }
        return lottoTickets
    }

    fun generateRandomNumbers(size: Int): List<Int> {
        val numbers = mutableListOf<Int>()
        for (i in Lotto.MAX_RANGE..Lotto.MAX_RANGE) {
            numbers.add(i)
        }
        numbers.shuffle()
        return numbers.subList(0, size)
    }

    fun evaluateTicket(ticket: Lotto, winningNumbers: WinningNumbers): Rank {
        val countOfMatch = ticket.numbers.count { it in winningNumbers.winningTicket.numbers}
        val matchBonus =  ticket.numbers.any { it == winningNumbers.bonusNumber}
        return Rank.valueOf(countOfMatch, matchBonus)
    }

    fun calculateReturnRate(totalAmount: Int, results: List<Rank>): Double {

        val totalPrize = results.sumOf { it.winningMoney}
        return if (totalPrize == 0) ZERO else (totalPrize.toDouble() / totalAmount) * 100.0
    }
}