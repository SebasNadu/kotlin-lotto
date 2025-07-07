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

class LottoController(
    private val lottoMachine: LottoMachine = LottoMachine(),
    private var purchaseSession: PurchaseSession = PurchaseSession()
) {
    fun run() {
        getPurchaseAmount()
        getManualTicketsAmount()
        InputView.printGetManualTicketsHeader()
        getManualTickets()
        getAutomaticTickets()

        OutputView.printTickets(purchaseSession)
//
//        val winningCombination = getWinningCombination()
//        val ticketsResult = ResultAnalyzer.evaluateTickets(lottoTickets, winningCombination)
//        val returnRate = ResultAnalyzer.calculateReturnRate(amount, ticketsResult)
//
//        OutputView.printResult(ticketsResult, returnRate)
    }

    private fun getPurchaseAmount() {
        purchaseSession = retryUntilSuccess {
            purchaseSession.updateAmount(InputView.getPurchaseAmount())
        }
    }

    private fun getManualTicketsAmount() {
        purchaseSession = retryUntilSuccess {
            purchaseSession.updateManualTicketsNumber(InputView.getManualTicketNumber())
        }
    }

    private fun getManualTickets() {
        purchaseSession = purchaseSession.updateManualTickets(
            List(purchaseSession.manualTicketsNumber) {
                retryUntilSuccess { Lotto.fromInts(InputView.getManualTicket(), LottoType.MANUAL) }
            }
        )
    }

    private fun getAutomaticTickets() {
        purchaseSession = lottoMachine.generateAutomaticTickets(purchaseSession)
    }

    private fun getWinningCombination(): WinningCombination {
        val winningLotto = retryUntilSuccess { Lotto.fromInts(InputView.getWinningNumbers()) }
        val winningCombination =
            retryUntilSuccess {
                WinningCombination(winningLotto, LottoNumber.from(InputView.getBonusNumber()))
            }
        return winningCombination
    }

    /**
     * Template function that accepts a lambda
     * returns only in case of successfully
     */
    private fun <T> retryUntilSuccess(block: () -> T): T {
        while (true) {
            try {
                return block()
            } catch (e: LottoException) {
                println(e.message)
            }
        }
    }
}
