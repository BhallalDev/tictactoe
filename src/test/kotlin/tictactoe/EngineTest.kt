package tictactoe

import org.junit.Assert.assertEquals
import org.junit.Test
import tictactoe.exception.InvalidConfigException

class EngineTest {
    private val reader = TestReader()
    private val writer = TestWriter()

    @Test
    fun `given a valid config it should greet with correct number of players and symbols`() {
        val config = validConfig()
        val subject = Engine(config)
        subject.startGame()
        assert(writer.lines[1].contains("3"))
        assert(writer.lines[3].contains("A"))
        assert(writer.lines[4].contains("B"))
        assert(writer.lines[5].contains("C"))

    }

    @Test
    fun `given a valid config with 3 players it should assign first two players as Human and last player as bot`() {
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
            playerCount = 3,
            symbols = listOf("A", "B", "C", "D"),
            reader = reader,
            writer = writer
        )
        val subject = Engine(config)
        subject.startGame()
    }

    @Test(expected = InvalidConfigException::class)
    fun `given a config where number of players is less than 3 then fails`() {
        val config = Config(
            playerCount = 2,
            symbols = listOf("A", "B"),
            reader = reader,
            writer = writer
        )
        val subject = Engine(config)
        subject.startGame()
    }

    @Test
    fun `should print initial state as soon as the game starts`() {
        val config = validConfig()
        val subject = Engine(config)
        subject.startGame()
        assertEquals("_ _ _", writer.lines[8].trim())
        assertEquals("_ _ _", writer.lines[9].trim())
        assertEquals("_ _ _", writer.lines[10].trim())
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

