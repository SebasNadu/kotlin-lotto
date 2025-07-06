package lotto.controller

import lotto.domain.Lotto
import lotto.domain.LottoMachine
import lotto.domain.LottoNumber
import lotto.domain.ResultAnalyzer
import lotto.domain.WinningCombination
import lotto.exceptions.LottoException
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(val lottoMachine: LottoMachine = LottoMachine()) {
    fun run() {
        val (amount, lottoTickets) = getTickets()

        OutputView.printTickets(lottoTickets)

        val winningCombination = getWinningCombination()
        val ticketsResult = ResultAnalyzer.evaluateTickets(lottoTickets, winningCombination)
        val returnRate = ResultAnalyzer.calculateReturnRate(amount, ticketsResult)

        OutputView.printResult(ticketsResult, returnRate)
    }

    private fun getTickets(): Pair<Int, List<Lotto>> {
        val amount =
            retryUntilSuccess({
                InputView.getPurchaseAmount().also { lottoMachine.validatePurchase(it) }
            })

        val lottoTickets = lottoMachine.purchase(amount)
        return Pair(amount, lottoTickets)
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
