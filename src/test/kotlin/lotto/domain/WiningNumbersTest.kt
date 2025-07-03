package lotto.domain

<<<<<<< HEAD
import lotto.exceptions.LottoException.InvalidBonusNumberException
=======
>>>>>>> 5f41921 (refactor: please read detail)
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class WiningNumbersTest {
<<<<<<< HEAD
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
=======

	@Test
	fun `Illegal WinningNumbers`() {
		assertThrows<IllegalArgumentException> {
			WinningCombination(Lotto(listOf(1, 1, 1)))
		}
	}

	@Test
	fun `throw if Winningnumbers do not have 6 number`() {
		assertThrows<IllegalArgumentException> {
			WinningCombination(Lotto(listOf(1, 2, 3)))
		}
	}

	@Test
	fun `throw if Winningnumbers have duplicated numbers`() {
		assertThrows<IllegalArgumentException> {
			val winningNumbers = Lotto(listOf(1, 1, 2, 3, 4, 5))
			WinningCombination(winningNumbers)
		}
	}

	@Test
	fun `throw if Winningnumbers do not have bonus number`() {
		assertThrows<IllegalArgumentException> {
			val winningNumbers = Lotto(listOf(1, 6, 2, 3, 4, 5))
			WinningCombination(winningNumbers)
		}
	}

	@Test
	fun `throw if bonus number is not unique`() {
		assertThrows<IllegalArgumentException> {
			val winningNumbers = Lotto(listOf(1, 2, 3, 4, 5, 6))
			WinningCombination(winningNumbers, 2)
		}

	}

	@Test
	fun `throw if bonus number is not in range 1 to 45`() {
		assertThrows<IllegalArgumentException> {
			val winningNumbers = Lotto(listOf(1, 2, 3, 4, 5, 6))
			WinningCombination(winningNumbers, 100)
		}
	}

	@Test
	fun `do not throw if Winningnumbers have 6 winning numbers and bonus number`() {
		assertDoesNotThrow {
			val winningNumbers = Lotto(listOf(1, 2, 3, 4, 5, 6))
			WinningCombination(winningNumbers, 7)
		}
	}

}
>>>>>>> 5f41921 (refactor: please read detail)
