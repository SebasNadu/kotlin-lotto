package lotto

import lotto.controller.LottoController
import lotto.domain.LottoMachine
import lotto.domain.PurchaseSession
import lotto.view.OutputView
import kotlin.system.exitProcess

/***
 * This app is a Lotto app that receives an amount from the user to purchase Lotto tickets.
 * The app waits for the winning numbers from the week and a bonus number, and with that
 * it will generate all the winning statistics for the user.
 *
 * @exception Exception to protect in case of unexpected behavior.
 */
fun main() {
    try {
        val controller = LottoController(LottoMachine(), PurchaseSession())
        controller.run()
    } catch (e: Exception) {
        OutputView.showErrorMessage(e.message ?: "[FATAL]: Unexpected error occurred.")
        exitProcess(1)
    }
}
