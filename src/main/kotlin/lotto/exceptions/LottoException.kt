package lotto.exceptions

import lotto.domain.Lotto
import lotto.domain.LottoMachine
import lotto.domain.LottoNumber

sealed class LottoException(msg: String) : IllegalArgumentException("\n[ERROR]: $msg") {
    class InvalidAmountFormatException(input: String) : LottoException("Wrong amount format: $input.\n")

    class InvalidAmountException(amount: Int) :
        LottoException(
            "Wrong amount: $amount, Amount should be at least ${LottoMachine.PRICE_OF_TICKET} ${LottoMachine.CURRENCY}" +
                ", divisible by ${LottoMachine.PRICE_OF_TICKET} and not bigger that 100,000.",
        )

    class InvalidLottoNumbersException : LottoException(
        "Invalid lotto numbers: Expected ${Lotto.LOTTO_PICK_SIZE} unique numbers in range " +
            "${LottoNumber.MINIMUM_NUMBER} to ${LottoNumber.MAXIMUM_NUMBER}.",
    )

    class InvalidNumbersFormatException(input: String) :
        LottoException("Invalid numbers format: '$input'. Expected numbers separated by commas.")

    class InvalidBonusNumberFormatException(input: String) :
        LottoException(
            "Invalid bonus number format: '$input'. Expected a number in the range of ${LottoNumber.MINIMUM_NUMBER} " +
                "to ${LottoNumber.MAXIMUM_NUMBER} and different to the winning numbers.",
        )

    class InvalidBonusNumberException(bonusNumber: LottoNumber) :
        LottoException(
            "Invalid bonus number: '$bonusNumber'. Expected a number in the range of ${LottoNumber.MINIMUM_NUMBER} " +
                "to ${LottoNumber.MAXIMUM_NUMBER} and different to the winning numbers.",
        )

    class InvalidLottoNumberException(value: Int) : LottoException(
        "Invalid lotto number: '$value'. Expected a number in the range of ${LottoNumber.MINIMUM_NUMBER} " +
            "to ${LottoNumber.MAXIMUM_NUMBER}.",
    )

    class InvalidManualTicketsSizeFormatException(input: String) : LottoException(
        "Invalid manual tickets number format: $input",
    )

    class InvalidManualTicketsNumberException(amount: Int) : LottoException(
        "Invalid amount of manual tickets: $amount: should be less or equal to the amount of purchased tickets.",
    )

    class InvalidManualTicketsException : LottoException("Invalid manual tickets generations.")

    class InvalidAutomaticTicketsException : LottoException("Invalid automatic tickets generations.")

    class InvalidTicketsEvaluation :
        LottoException("Invalid ticket evaluation: Winning numbers are mandatory for the calculation.")
}
