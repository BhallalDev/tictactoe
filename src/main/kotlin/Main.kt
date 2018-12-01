import tictactoe.Config
import tictactoe.Engine
import tictactoe.io.ConsoleReader
import tictactoe.io.ConsoleWriter

fun main(args: Array<String>) {
    val config = Config(
        playerCount = 3,
        symbols = listOf("A", "B", "C"),
        reader = ConsoleReader(),
        writer = ConsoleWriter()
    )

    val engine = Engine(config)
    engine.startGame()
    engine.close()
}