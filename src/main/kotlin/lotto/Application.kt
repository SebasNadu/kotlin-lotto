package lotto

import lotto.controller.LottoController
import kotlin.system.exitProcess

/***
 * This app is a Lotto app, that receive an amount from the user to purchase Lotto tickets.
 * The app waits for the winning numbers from the week and a bonus number, and with that
 * it will generate all the winning statistics for the user.
 *
 * @exception Any Protected Exception in case unexpected behavior.
 */
fun main() {
	try {
		val controller = LottoController()
		controller.run()
	} catch (e: Exception) {
		println(e.message ?: "Unexpected error occurred.")
		exitProcess(1)
	}
}
