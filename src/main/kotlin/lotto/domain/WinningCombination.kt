package lotto.domain

import lotto.exceptions.LottoException

/**
 * the class contain winning ticket and bonus number to eval user's Lotto tickets
 */
class WinningCombination(val winningLotto: Lotto, val bonusNumber: LottoNumber) {
    init {
        if (winningLotto.contains(bonusNumber)) {
            throw LottoException.InvalidBonusNumberException(bonusNumber)
        }
    }
}
