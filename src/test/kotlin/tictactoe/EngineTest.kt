package tictactoe

import org.junit.Test
import tictactoe.exception.InvalidConfigException
import tictactoe.io.Reader
import tictactoe.io.Writer

class EngineTest {
    private val reader = TestReader()
    private val writer = TestWriter()

    @Test
    fun `given a config should greet with correct number of players and symbols`() {
        val config = validConfig()
        val subject = Engine(config)
        subject.startGame()
        assert(writer.lines[1].contains("3"))
        assert(writer.lines[3].contains("A"))
        assert(writer.lines[4].contains("B"))
        assert(writer.lines[5].contains("C"))

    }

    @Test
    fun `given a config with 3 players should assign first two players as Human and last player as bot`() {
        val config = validConfig()
        val subject = Engine(config)
        subject.startGame()
        assert(writer.lines[1].contains("3"))
        assert(writer.lines[3].contains("Human-1"))
        assert(writer.lines[4].contains("Human-2"))
        assert(writer.lines[5].contains("Bot"))
    }

    @Test(expected = InvalidConfigException::class)
    fun `given a config where number of players and symbols does not match then fails with InvalidConfigException`() {
        val config = Config(
            playerCount = 2,
            symbols = listOf("A", "B", "C"),
            reader = reader,
            writer = writer
        )
        val subject = Engine(config)
        subject.startGame()
    }

    private fun validConfig(): Config {
        return Config(
            playerCount = 3,
            symbols = listOf("A", "B", "C"),
            reader = reader,
            writer = writer
        )
    }
}

class TestReader : Reader {
    override fun readLine(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

class TestWriter : Writer {
    val lines = mutableListOf<String>()

    override fun println(output: String) {
        lines.add(output)
    }
}