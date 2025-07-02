package domain

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class WiningNumbersTest {

    @Test
    fun `Illegal WinningNumbers`() {
        assertThrows<IllegalArgumentException> {
            WiningNumbers()
        }
    }

    @Test
    fun `throw if Winningnumbers do not have 6 number`() {
        assertThrows<IllegalArgumentException> {
            WiningNumbers()
        }
    }

    @Test
    fun `throw if Winningnumbers have duplicated numbers`() {
        assertThrows<IllegalArgumentException> {
            val winningNumbers = listOf(1, 1, 2, 3, 4, 5)
            WiningNumbers(winningNumbers)
        }
    }

    @Test
    fun `throw if Winningnumbers do not have bonus number`() {
        assertThrows<IllegalArgumentException> {
            val winningNumbers = listOf(1, 6, 2, 3, 4, 5)
            WiningNumbers(winningNumbers)
        }
    }

    @Test
    fun `throw if bonus number is not unique`() {
        assertThrows<IllegalArgumentException> {
            val winningNumbers = listOf(1, 2, 3, 4, 5, 6)
            WiningNumbers(winningNumbers, 2)
        }

    }

    fun `throw if bonus number is not in range 1 to 45`() {
        assertThrows<IllegalArgumentException> {
            WiningNumbers()
        }
    }

    fun `do not throw if Winningnumbers have 6 winning numbers and bonus number`() {
        assertDoesNotThrow {
            WiningNumbers()
        }
    }

}