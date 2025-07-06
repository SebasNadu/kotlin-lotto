package lotto.domain

import lotto.exceptions.LottoException.InvalidLottoNumberException

class LottoNumber private constructor(private val value: Int): Comparable<LottoNumber> {

    override fun toString(): String = value.toString()

    override fun equals(other: Any?): Boolean = this == other || (other is LottoNumber && value == other.value)

    override fun hashCode(): Int = value

    override fun compareTo(other: LottoNumber): Int = value - other.value

    companion object {
        const val MINIMUM_NUMBER = 1
        const val MAXIMUM_NUMBER = 45
        private val NUMBERS: Map<Int, LottoNumber> =
            (MINIMUM_NUMBER..MAXIMUM_NUMBER).associateWith(::LottoNumber)

        fun from(value: Int): LottoNumber {
            return NUMBERS[value] ?: throw InvalidLottoNumberException(value)
        }
    }
}