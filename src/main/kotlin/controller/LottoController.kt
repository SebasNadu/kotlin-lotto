package controller

import domain.Lotto
import domain.Rank
import domain.WinningNumbers
import services.LottoService
import util.LottoUtils.retry
import view.InputView
import view.OutputView

class LottoController {

    fun run() {
        val amount = retry({
            InputView.getPurchaseAmount().also { LottoService.purchaseAmountValidator(it) }
        })
        val lottoTickets = LottoService.generateLottoTickets(amount)
        OutputView.printTickets(lottoTickets)
        val winningLotto: Lotto = retry { Lotto(InputView.getWinningNumbers()) }
        val winningNumbers = retry {
            WinningNumbers(winningLotto, InputView.getBonusNumber())
        }
        val result = evaluateTickets(lottoTickets, winningNumbers)
        val returnRate = LottoService.calculateReturnRate(amount, result)
        OutputView.printResult(result, returnRate)
    }

    private fun evaluateTickets(tickets: List<Lotto>, winningNumbers: WinningNumbers): List<Rank> {
        val ranks = mutableListOf<Rank>()
        for (ticket in tickets) {
            val result = LottoService.evaluateTicket(ticket, winningNumbers)
            ranks.add(result)
        }
        return ranks
    }

}