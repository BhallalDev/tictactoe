package tictactoe.io

interface Writer {
    fun println(output: String)
    fun print(output: String)
}

class ConsoleWriter : Writer {
    override fun print(output: String) {
        print(output)
    }

    override fun println(output: String) {
        println(output)
    }
}