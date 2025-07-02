package view

object InputView {
    fun getPurchaseAmount(): Int {
        println(Constants.PURCHASE_PROMPT)
        return readln().trim().toIntOrNull() ?: throw IllegalArgumentException()
    }

    object Constants {
        const val PURCHASE_PROMPT = "Please enter the purchase amount."
        const val WINNING_NUMBERS_PROMPT = "Please enter last week’s winning numbers."
        const val BONUS_NUMBER_PROMPT = "Please enter the bonus number."
    }
}