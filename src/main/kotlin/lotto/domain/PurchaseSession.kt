package lotto.domain

data class PurchaseSession(
    val amount: Int = 0,
    val automaticTickets: List<Lotto> = emptyList(),
    val manualTickets: List<Lotto> = emptyList(),
    val manualTicketsSize: Int = manualTickets.size,
    val allTicketsSize: Int = manualTickets.size + automaticTickets.size,
    val allTickets: List<Lotto> = automaticTickets + manualTickets,
    val winningCombination: WinningCombination? = null,
    val ticketsResult: List<Rank> = emptyList(),
    val returnRate: Double = 0.0,
)