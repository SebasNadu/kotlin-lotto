package controller

import domain.Lotto
import services.LottoService
import util.LottoUtils.retry
import view.InputView

class LottoController {

    fun run() {
        val amount = retry(InputView::getPurchaseAmount, LottoService::purchaseAmountValidator)
        val lottoTickets = LottoService.generateLottoTickets(amount)
    }

}