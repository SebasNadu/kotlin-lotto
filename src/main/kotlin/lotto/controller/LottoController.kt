package lotto.controller

import lotto.domain.Lotto
import lotto.domain.LottoMachine
import lotto.domain.LottoNumber
import lotto.domain.Rank
import lotto.domain.ResultAnalyzer
import lotto.domain.WinningCombination
import lotto.domain.getRank
import lotto.util.LottoUtils
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(val lottoMachine: LottoMachine = LottoMachine()) {
    fun run() {
        val (amount, lottoTickets) = getTickets()

        OutputView.printTickets(lottoTickets)

        val winningCombination = getWinningCombination()
        val (result, returnRate) = getEvaluationResult(lottoTickets, winningCombination, amount)

        OutputView.printResult(result, returnRate)
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

    private fun getEvaluationResult(
        lottoTickets: List<Lotto>,
        winningCombination: WinningCombination,
        amount: Int,
    ): Pair<List<Rank>, Double> {
        val result = evaluateTickets(lottoTickets, winningCombination)
        val returnRate = ResultAnalyzer.calculateReturnRate(amount, result)
        return Pair(result, returnRate)
    }

    private fun evaluateTickets(
        tickets: List<Lotto>,
        winningCombination: WinningCombination,
    ): List<Rank> = tickets.map { it.getRank(winningCombination) }
}
