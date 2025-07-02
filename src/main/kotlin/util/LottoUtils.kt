package util

object LottoUtils {
    fun <T> retry(
        block: () -> T,
    ): T {
        while (true) {
            try {
                block()
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }
}