package tictactoe.io

interface Writer {
    fun println(output: String)
}

class ConsoleWriter : Writer {
    override fun println(output: String) {
        println(output)
    }
}