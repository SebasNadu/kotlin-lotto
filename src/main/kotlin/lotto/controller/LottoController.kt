package lotto.controller

import lotto.domain.Lotto
import lotto.domain.Rank
import lotto.domain.WinningCombination
import lotto.services.LottoService
import lotto.util.LottoUtils
import lotto.view.InputView
import lotto.view.OutputView

class LottoController {
    fun run() {
        val (amount, lottoTickets) = getTickets()

        OutputView.printTickets(lottoTickets)

        val winningCombination = getWinningCombination()
        val (result, returnRate) = getEvaluationResult(lottoTickets, winningCombination, amount)

        OutputView.printResult(result, returnRate)
    }

    private fun getEvaluationResult(
        lottoTickets: List<Lotto>,
        winningCombination: WinningCombination,
        amount: Int,
    ): Pair<List<Rank>, Double> {
        val result = evaluateTickets(lottoTickets, winningCombination)
        val returnRate = LottoService.calculateReturnRate(amount, result)
        return Pair(result, returnRate)
    }

    private fun getWinningCombination(): WinningCombination {
        val winningTicket = LottoUtils.retryUntilSuccess { Lotto(InputView.getWinningNumbers()) }
        val winningCombination =
            LottoUtils.retryUntilSuccess {
                WinningCombination(winningTicket, InputView.getBonusNumber())
            }
        return winningCombination
    }

    private fun evaluateTickets(
        tickets: List<Lotto>,
        winningCombination: WinningCombination,
    ): List<Rank> {
        val ranks = mutableListOf<Rank>()
        for (ticket in tickets) {
            val result = LottoService.getRankFrom(ticket, winningCombination)
            ranks.add(result)
        }
        return ranks
    }

    private fun getTickets(): Pair<Int, List<Lotto>> {
        val amount =
            LottoUtils.retryUntilSuccess({
                InputView.getPurchaseAmount().also { LottoService.purchaseAmountValidator(it) }
            })

        val lottoTickets = LottoService.generateLottoTickets(amount)
        return Pair(amount, lottoTickets)
    }
}
