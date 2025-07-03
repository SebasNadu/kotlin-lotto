package lotto.services

import lotto.domain.Lotto
import lotto.domain.Rank
import lotto.domain.WinningCombination
import lotto.exceptions.LottoException

object LottoService {
	private const val ZERO_DOUBLE = 0.0

	fun purchaseAmountValidator(amount: Int) {
		if (amount < Lotto.Companion.PRICE_OF_TICKET || amount % Lotto.Companion.PRICE_OF_TICKET != 0) throw LottoException.InvalidAmountException(
			amount
		)
	}

	fun generateLottoTickets(amount: Int): List<Lotto> {
		val numberTickets = amount / Lotto.Companion.PRICE_OF_TICKET
		return List(numberTickets) { getLottoTicket() }
	}

	fun getRankFrom(ticket: Lotto, winningNumbers: WinningCombination): Rank {
		val countOfMatch = ticket.numbers.count { it in winningNumbers.winningTicket.numbers }
		val matchBonus = ticket.numbers.any { it == winningNumbers.bonusNumber }
		return Rank.valueOf(countOfMatch, matchBonus)
	}

	fun calculateReturnRate(totalAmount: Int, results: List<Rank>): Double {
		val totalPrize = results.sumOf { it.winningMoney }
		return if (totalPrize == 0) ZERO_DOUBLE else (totalPrize.toDouble() / totalAmount) * 100.0
	}

	private fun getLottoTicket(): Lotto {
		val numbers = generateRandomNumbers(Lotto.Companion.LOTTO_PICK_SIZE)
		return Lotto(numbers)
	}

	private fun generateRandomNumbers(size: Int): List<Int> {
		return (Lotto.MIN_RANGE..Lotto.MAX_RANGE)
			.shuffled()
			.take(size)
	}
}