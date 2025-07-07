package lotto.controller

import lotto.domain.Lotto
import lotto.domain.LottoMachine
import lotto.domain.LottoNumber
import lotto.domain.LottoType
import lotto.domain.PurchaseSession
import lotto.domain.ResultAnalyzer
import lotto.domain.WinningCombination
import lotto.exceptions.LottoException
import lotto.view.InputView
import lotto.view.OutputView
import kotlin.system.exitProcess

class LottoController(
    private val lottoMachine: LottoMachine = LottoMachine(),
    private var purchaseSession: PurchaseSession = PurchaseSession(),
) {
    fun run() {
        getPurchaseAmount()
        getManualTickets()
        getAutomaticTickets()

        OutputView.printTickets(purchaseSession)

        getWinningCombination()
        getTicketsStatistics()

        OutputView.printResult(purchaseSession)
    }

    private fun getPurchaseAmount() {
        purchaseSession =
            retryUntilSuccess {
                purchaseSession.updateAmount(InputView.getPurchaseAmount())
            }
    }

    private fun getManualTickets() {
        getManualTicketsAmount()
        if (purchaseSession.manualTicketsNumber == 0) return

        InputView.printGetManualTicketsHeader()
        purchaseSession =
            purchaseSession.updateManualTickets(
                List(purchaseSession.manualTicketsNumber) {
                    retryUntilSuccess { Lotto.fromInts(InputView.getManualTicket(), LottoType.MANUAL) }
                },
            )
    }

    private fun getManualTicketsAmount() {
        purchaseSession =
            retryUntilSuccess {
                purchaseSession.updateManualTicketsNumber(InputView.getManualTicketNumber())
            }
    }

    private fun getAutomaticTickets() {
        purchaseSession = lottoMachine.generateAutomaticTickets(purchaseSession)
    }

    private fun getWinningCombination() {
        val winningLotto = retryUntilSuccess { Lotto.fromInts(InputView.getWinningNumbers()) }
        val winningCombination =
            retryUntilSuccess {
                WinningCombination(winningLotto, LottoNumber.from(InputView.getBonusNumber()))
            }
        purchaseSession = purchaseSession.updateWinningCombination(winningCombination)
    }

    private fun getTicketsStatistics() {
        val ticketsRank = ResultAnalyzer.evaluateTickets(purchaseSession)
        purchaseSession = purchaseSession.updateTicketsRank(ticketsRank)
        val returnRate = ResultAnalyzer.calculateReturnRate(purchaseSession)
        purchaseSession = purchaseSession.updateReturnRate(returnRate)
    }

    /**
     * Template function that accepts a lambda
     * returns only in case of successfully
     */
    private fun <T> retryUntilSuccess(block: () -> T): T {
        repeat(RETRY_LIMIT) {
            try {
                return block()
            } catch (e: LottoException) {
                OutputView.showErrorMessage(e.message ?: "Unexpected error on the retry until success.")
            }
        }
        exitProcess(1)
    }

    companion object {
        private const val RETRY_LIMIT = 100
    }
}
