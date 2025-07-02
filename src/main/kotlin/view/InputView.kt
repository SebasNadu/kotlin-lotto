package view

object InputView {
    fun getPurchaseAmount(): Int {
        println(Constants.PURCHASE_PROMPT)
        return readln().trim().toIntOrNull() ?: throw IllegalArgumentException()
    }

    fun getWinningNumbers(): List<Int> {
        println(Constants.WINNING_NUMBERS_PROMPT)
        return readln()
            .split(',')
            .map { it.trim().toIntOrNull()
                ?:  throw IllegalArgumentException()}
    }

    fun getBonusNumber(): Int {
        println(Constants.BONUS_NUMBER_PROMPT)
        return readln().trim().toIntOrNull() ?: throw IllegalArgumentException()
    }

    object Constants {
        const val PURCHASE_PROMPT = "Please enter the purchase amount."
        const val WINNING_NUMBERS_PROMPT = "Please enter last week’s winning numbers."
        const val BONUS_NUMBER_PROMPT = "Please enter the bonus number."
    }
}