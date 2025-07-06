package lotto.controller

import lotto.domain.Lotto
import lotto.domain.LottoMachine
import lotto.domain.LottoNumber
import lotto.domain.ResultAnalyzer
import lotto.domain.WinningCombination
import lotto.util.LottoUtils
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
            LottoUtils.retryUntilSuccess({
                InputView.getPurchaseAmount().also { lottoMachine.validatePurchase(it) }
            })

        val lottoTickets = lottoMachine.purchase(amount)
        return Pair(amount, lottoTickets)
    }

    private fun getWinningCombination(): WinningCombination {
        val winningLotto = LottoUtils.retryUntilSuccess { Lotto.fromInts(InputView.getWinningNumbers()) }
        val winningCombination =
            LottoUtils.retryUntilSuccess {
                WinningCombination(winningLotto, LottoNumber.from(InputView.getBonusNumber()))
            }
        return winningCombination
    }
}
