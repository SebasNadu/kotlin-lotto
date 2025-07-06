package lotto.domain

import lotto.exceptions.LottoException.InvalidLottoNumberException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoNumberTest {

    @Test
    fun `should create LottoNumber from valid value`() {
        val number = LottoNumber.from(10)
        assertThat(number.toString()).isEqualTo("10")
    }

    @Test
    fun `should throw exception for number below minimum`() {
        assertThrows<InvalidLottoNumberException> {
            LottoNumber.from(0)
        }
    }

    @Test
    fun `should throw exception for number above maximum`() {
        assertThrows<InvalidLottoNumberException> {
            LottoNumber.from(46)
        }
    }

    @Test
    fun `should return same instance for same value`() {
        val a = LottoNumber.from(7)
        val b = LottoNumber.from(7)
        assertThat(a === b).isTrue()
        assertThat(a).isEqualTo(b)
    }

    @Test
    fun `should compare LottoNumbers by value`() {
        val low = LottoNumber.from(3)
        val high = LottoNumber.from(40)
        assertThat(low < high).isTrue()
        assertThat(high > low).isTrue()
    }

    @Test
    fun `should produce consistent hashCode`() {
        val a = LottoNumber.from(15)
        val b = LottoNumber.from(15)
        assertThat(a.hashCode()).isEqualTo(b.hashCode())
    }

    @Test
    fun `toString should return string representation of value`() {
        val number = LottoNumber.from(33)
        assertThat(number.toString()).isEqualTo("33")
    }
}
