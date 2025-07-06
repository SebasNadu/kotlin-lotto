package lotto.exceptions

import lotto.domain.Lotto
import lotto.domain.LottoMachine
import lotto.domain.LottoNumber

sealed class LottoException(msg: String) : IllegalArgumentException("[ERROR]: $msg") {
    class InvalidAmountFormatException(input: String) : LottoException("Wrong amount format: $input.\n")

    class InvalidAmountException(amount: Int) :
        LottoException(
            "Wrong amount: $amount, Amount should be at least ${LottoMachine.PRICE_OF_TICKET} ${LottoMachine.CURRENCY}" +
                    "and divisible by ${LottoMachine.PRICE_OF_TICKET}.\n",
        )

    class InvalidLottoNumbersException() : LottoException(
        "Invalid lotto numbers: Expected ${Lotto.LOTTO_PICK_SIZE} unique numbers in range " +
                "${LottoNumber.MINIMUM_NUMBER} to ${LottoNumber.MAXIMUM_NUMBER}.\n",
    )

    class InvalidWinningNumbersFormatException(input: String) :
        LottoException("Invalid winning numbers format: '$input'. Expected numbers separated by commas.")

    class InvalidWinningNumbersException() :
        LottoException(
            "Invalid winning numbers: Expected ${Lotto.LOTTO_PICK_SIZE} numbers in range + " +
                    "${LottoNumber.MINIMUM_NUMBER} to ${LottoNumber.MAXIMUM_NUMBER}\n",
        )

    class InvalidBonusNumberFormatException(input: String) :
        LottoException(
            "Invalid bonus number format: '$input'. Expected a number in the range of ${LottoNumber.MINIMUM_NUMBER} " +
                    "to ${LottoNumber.MAXIMUM_NUMBER} and different to the winning numbers.",
        )

    class InvalidBonusNumberException(bonusNumber: Int) :
        LottoException(
            "Invalid bonus number: '$bonusNumber'. Expected a number in the range of ${LottoNumber.MINIMUM_NUMBER} " +
                    "to ${LottoNumber.MAXIMUM_NUMBER} and different to the winning numbers.",
        )

    class InvalidLottoNumber(value: Int) : LottoException(
        "Invalid lotto number: '$value'. Expected a number in the range of ${LottoNumber.MINIMUM_NUMBER} " +
                "to ${LottoNumber.MAXIMUM_NUMBER}.",
    )
}
