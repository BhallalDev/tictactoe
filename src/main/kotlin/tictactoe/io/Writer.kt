package tictactoe.io

interface Writer {
    fun println(output: String)
    fun print(output: String)
}

class ConsoleWriter : Writer {
    override fun print(output: String) {
        kotlin.io.print(output)
    }

    override fun println(output: String) {
        kotlin.io.println(output)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ConsoleWriter) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }


}