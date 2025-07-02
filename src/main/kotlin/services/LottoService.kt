package services

import domain.Lotto
import kotlin.random.Random

object LottoService {
    fun purchaseAmountValidator(amount: Int): Boolean {
        return amount >= 1000 && amount % 1000 == 0
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
}