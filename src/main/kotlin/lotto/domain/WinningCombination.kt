package lotto.domain

import lotto.exceptions.LottoException
import lotto.util.LottoUtils

/**
 * the class contain winning ticket and bonus number to eval user's Lotto tickets
 */
class WinningCombination(val winningTicket: Lotto, val bonusNumber: Int = 0) {
    init {
        LottoUtils.requireOrThrow(
            LottoUtils.isInRange(bonusNumber),
            LottoException.InvalidBonusNumberException(bonusNumber),
        )
        LottoUtils.requireOrThrow(
            winningTicket.numbers.none { it == bonusNumber },
            LottoException.InvalidBonusNumberException(bonusNumber),
        )
    }
}
