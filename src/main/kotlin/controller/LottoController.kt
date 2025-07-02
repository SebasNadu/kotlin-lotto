package controller

import domain.Lotto
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
    }

}