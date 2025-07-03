package lotto.exceptions

import lotto.domain.Lotto

sealed class LottoException(msg: String) : IllegalArgumentException("[ERROR]: $msg") {
    class InvalidAmountFormatException(input: String) : LottoException("Wrong amount format: $input.\n")

    class InvalidAmountException(amount: Int) :
        LottoException(
            "Wrong amount: $amount, Amount should be at least ${Lotto.PRICE_OF_TICKET} ${Lotto.CURRENCY}" +
                "and divisible by ${Lotto.PRICE_OF_TICKET}.\n",
        )

    class InvalidWinningNumbersFormatException(input: String) :
        LottoException("Invalid winning numbers format: '$input'. Expected numbers separated by commas.")

    class InvalidWinningNumbersException() :
        LottoException(
            "Invalid winning numbers: Expected ${Lotto.LOTTO_PICK_SIZE} numbers in range + " +
                "${Lotto.MIN_RANGE} to ${Lotto.MAX_RANGE}\n",
        )

    class InvalidBonusNumberFormatException(input: String) :
        LottoException(
            "Invalid bonus number format: '$input'. Expected a number in the range of ${Lotto.MIN_RANGE} " +
                "to ${Lotto.MAX_RANGE} and different to the winning numbers.",
        )

    class InvalidBonusNumberException(bonusNumber: Int) :
        LottoException(
            "Invalid bonus number format: '$bonusNumber'. Expected a number in the range of ${Lotto.MIN_RANGE} " +
                "to ${Lotto.MAX_RANGE} and different to the winning numbers.",
        )
}
