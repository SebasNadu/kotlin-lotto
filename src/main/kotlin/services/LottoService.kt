package services

object LottoService {
    fun purchaseAmountValidator(amount: Int): Boolean {
        return amount >= 1000 && amount % 1000 == 0
    }
}