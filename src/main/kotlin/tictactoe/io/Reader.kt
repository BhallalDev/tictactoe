package tictactoe.io

interface Reader {
    fun readLine(): String?

}

class ConsoleReader: Reader {
    override fun readLine(): String?{
        return kotlin.io.readLine()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ConsoleReader) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }

}
