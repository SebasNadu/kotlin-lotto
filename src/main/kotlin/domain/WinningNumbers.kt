package domain

class WinningNumbers(val winningTicket: Lotto, val bonusNumber: Int = 0) {
        init {
            require(bonusNumber in 1..45)
            require(winningTicket.numbers.none { it == bonusNumber })
        }
}