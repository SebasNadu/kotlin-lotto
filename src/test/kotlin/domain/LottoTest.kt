package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource


class LottoTest {
    @Test
    fun `Illegal Lotto`() {
        assertThrows<IllegalArgumentException> {
            Lotto()
        }
    }
}

