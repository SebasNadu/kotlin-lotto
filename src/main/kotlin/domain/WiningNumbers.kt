package domain

class WiningNumbers(winningNumbers: List<Int> = listOf(), val bonusNumber: Int = 0) {
    val ticket: Lotto = Lotto(winningNumbers)
        init {
            require(bonusNumber != 0)
            require(ticket.numbers.none { it == bonusNumber })
        }
}