package lotto.domain

import lotto.exceptions.LottoException.InvalidBonusNumberException
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class WiningNumbersTest {
    private fun createLotto(vararg numbers: Int): Lotto = Lotto(numbers.map(LottoNumber::from).toSet())

    @Test
    fun `should throw if bonus number is duplicated in winning numbers`() {
        val winningLotto = createLotto(1, 2, 3, 4, 5, 6)
        val bonus = LottoNumber.from(3)
        assertThrows<InvalidBonusNumberException> {
            WinningCombination(winningLotto, bonus)
        }
    }

    @Test
    fun `should not throw if bonus number is valid and not duplicated`() {
        val winningLotto = createLotto(1, 2, 3, 4, 5, 6)
        val bonus = LottoNumber.from(7)
        assertDoesNotThrow {
            WinningCombination(winningLotto, bonus)
        }
    }
}
