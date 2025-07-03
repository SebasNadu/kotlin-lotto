package lotto.domain

/**
 * This enum class is a list of prices for the lotto winner
 */
enum class Rank(val countOfMatch: Int, val winningMoney: Int, val requiresBonus: Boolean = false) {
    FIRST(6, 2_000_000_000, false),
    SECOND(5, 30_000_000, true),
    THIRD(5, 1_500_000, false),
    FOURTH(4, 50_000, false),
    FIFTH(3, 5_000, false),
    MISS(0, 0),
    ;

    companion object {
        fun valueOf(
            countOfMatch: Int,
            matchBonus: Boolean = false,
        ): Rank {
            if (countOfMatch == SECOND.countOfMatch) {
                return if (matchBonus) SECOND else THIRD
            }
            return entries.find {
                it.countOfMatch == countOfMatch
            } ?: MISS
        }
    }
}
