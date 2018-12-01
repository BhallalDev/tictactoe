package tictactoe

import tictactoe.io.Writer

class TestWriter : Writer {

    var currentLine = ""

    override fun print(output: String) {
        currentLine += output
    }

    val lines = mutableListOf<String>()

    override fun println(output: String) {
        lines.add(currentLine+output)
        currentLine=""
    }
}