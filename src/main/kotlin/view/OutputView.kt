package view

import domain.Lotto

object OutputView {
    fun printTickets(tickets: List<Lotto>) {
        val numberOfTickets = tickets.size
        println("${Constants.TICKETS_NUMBERS_PROMPT} " +
                "$numberOfTickets ${pluralizeTicket(numberOfTickets)}.")
        for (ticket in tickets) {
            println(ticket)
        }
    }

    private fun pluralizeTicket(size: Int): String {
        return if ( size == 1) "ticket" else "tickets"
    }

    object Constants {
        const val TICKETS_NUMBERS_PROMPT = "You have purchased"
    }
}