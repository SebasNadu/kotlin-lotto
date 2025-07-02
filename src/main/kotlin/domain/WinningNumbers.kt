package domain

class WinningNumbers(val winningNumbers: Lotto, val bonusNumber: Int = 0) {
        init {
            require(bonusNumber in 1..45)
            require(winningNumbers.numbers.none { it == bonusNumber })
        }
}