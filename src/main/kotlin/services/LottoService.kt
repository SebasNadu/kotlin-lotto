package services

import domain.Lotto
import domain.Rank
import domain.WinningNumbers

object LottoService {
    fun purchaseAmountValidator(amount: Int) {
        if (amount < 1000 || amount % 1000 != 0)
            throw IllegalArgumentException()
    }

    fun generateLottoTickets(amount: Int): List<Lotto> {
        val numberTickets = amount / 1000
        val lottoTickets: MutableList<Lotto> = mutableListOf()
        repeat(numberTickets) {
            val numbers = generateRandomNumbers(6)
            lottoTickets.add(Lotto(numbers))
        }
        return lottoTickets
    }

    fun generateRandomNumbers(size: Int): List<Int> {
        val numbers = mutableListOf<Int>()
        for (i in 1..45) {
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
        if (totalPrize == 0) return 0.0
        return (totalPrize.toDouble() / totalAmount) * 100
    }
}