package domain

class WinningNumbers(val winningTicket: Lotto, val bonusNumber: Int = 0) {
        init {
            require(bonusNumber in Lotto.MIN_SIZE..Lotto.MAX_RANGE)
            require(winningTicket.numbers.none { it == bonusNumber })
        }
}