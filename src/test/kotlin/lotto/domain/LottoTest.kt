package lotto.domain

<<<<<<< HEAD
import lotto.exceptions.LottoException.InvalidLottoNumberException
import lotto.exceptions.LottoException.InvalidLottoNumbersException
=======
>>>>>>> 5f41921 (refactor: please read detail)
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTest {
<<<<<<< HEAD
    private fun createLotto(vararg numbers: Int): Lotto = Lotto(numbers.map(LottoNumber::from).toSet())

    @Test
    fun `should create lotto with valid numbers`() {
        assertThat(createLotto(1, 2, 3, 4, 5, 6)).isInstanceOf(Lotto::class.java)
    }

    @Test
    fun `Lotto numbers are in range`() {
        val lotto = createLotto(1, 45, 2, 3, 4, 5)
        assertThat(lotto.numbers).hasSize(6)
    }

    @Test
    fun `should throw when lotto has not numbers`() {
        assertThrows<InvalidLottoNumbersException> {
            Lotto(emptySet())
        }
    }

    @Test
    fun `should throw when lotto has duplicated numbers`() {
        assertThrows<InvalidLottoNumbersException> {
            createLotto(1, 1, 2, 3, 4, 5)
        }
    }

    @Test
    fun `should throw when lotto has numbers out of range`() {
        assertThrows<InvalidLottoNumberException> {
            createLotto(0, 46, 2, 3, 4, 5)
        }
    }

    @Test
    fun `should format to string correctly`() {
        val ticket = createLotto(1, 2, 3, 4, 5, 6)
        assertThat(ticket.toString()).isEqualTo("[1, 2, 3, 4, 5, 6]")
    }

    @Test
    fun `should return FIRST rank when 6 numbers match`() {
        val winning = createLotto(1, 2, 3, 4, 5, 6)
        val player = createLotto(1, 2, 3, 4, 5, 6)
        val winningCombination = WinningCombination(winning, LottoNumber.from(7))
        assertThat(player.getRank(winningCombination)).isEqualTo(Rank.FIRST)
    }

    @Test
    fun `should return SECOND rank when 5 match and bonus number`() {
        val winning = createLotto(1, 2, 3, 4, 5, 6)
        val player = createLotto(1, 2, 3, 4, 5, 7)
        val winningCombination = WinningCombination(winning, LottoNumber.from(7))
        assertThat(player.getRank(winningCombination)).isEqualTo(Rank.SECOND)
    }

    @Test
    fun `should return THIRD rank when 5 match without bonus`() {
        val winning = createLotto(1, 2, 3, 4, 5, 6)
        val player = createLotto(1, 2, 3, 4, 5, 7)
        val winningCombination = WinningCombination(winning, LottoNumber.from(8))
        assertThat(player.getRank(winningCombination)).isEqualTo(Rank.THIRD)
    }

    @Test
    fun `should return NONE when less than 3 match`() {
        val winning = createLotto(1, 2, 3, 4, 5, 6)
        val player = createLotto(7, 8, 9, 10, 11, 12)
        val winningCombination = WinningCombination(winning, LottoNumber.from(13))
        assertThat(player.getRank(winningCombination)).isEqualTo(Rank.MISS)
    }
}
=======
	@Test
	fun `Illegal Lotto`() {
		assertThrows<IllegalArgumentException> {
			Lotto()
		}
	}

	@Test
	fun `Lotto has 6 numbers`() {
		val ticket = listOf(1, 2, 3, 4, 5, 6)
		assertThat(Lotto(ticket))
	}

	@Test
	fun `Lotto has 6 not unique numbers`() {
		val ticket = listOf(1, 1, 2, 3, 4, 5)
		assertThrows<IllegalArgumentException> {
			Lotto(ticket)
		}
	}

	@Test
	fun `Lotto numbers are in range`() {
		val ticket = listOf(1, 2, 3, 4, 5, 45)
		assertThat(Lotto(ticket))
	}

	@Test
	fun `Lotto numbers are not in range`() {
		val ticket = listOf(1, 100, 2, 3, 4, 5)
		assertThrows<IllegalArgumentException> {
			Lotto(ticket)
		}
	}

	@Test
	fun `Lotto to string with the correct format`() {
		val ticket = Lotto(listOf(1, 2, 3, 4, 5, 6))
		assertThat(ticket.toString()).isEqualTo("[1, 2, 3, 4, 5, 6]")
	}
}

>>>>>>> 5f41921 (refactor: please read detail)
