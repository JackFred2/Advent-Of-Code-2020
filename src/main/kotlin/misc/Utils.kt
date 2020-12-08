package misc

class Utils {
    companion object Funcs {
        fun loadFile(path: String): String? {
            return this::class.java.classLoader.getResource(path)?.readText()
        }
    }
}