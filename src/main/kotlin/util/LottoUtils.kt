package util

object LottoUtils {
    fun <T> retry(
        get: () -> T,
        validator: (T) -> T
    ): T {
        while (true) {
            try {
                return validator(get())
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }
}