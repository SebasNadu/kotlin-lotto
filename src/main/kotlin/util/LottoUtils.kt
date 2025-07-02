package util

object LottoUtils {
    fun <T> retry(
        get: () -> T,
        validator: (T) -> Boolean
    ): T {
        while (true) {
            try {
                val input = get()
                if (validator(input)) return input
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }
}