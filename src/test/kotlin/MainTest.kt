import org.junit.Assert.*
import org.junit.Test
import tictactoe.Config
import tictactoe.exception.InvalidConfigException
import tictactoe.io.ConsoleReader
import tictactoe.io.ConsoleWriter
import java.io.ByteArrayInputStream

class MainTest {
    @Test
    fun `given a valid config, loadConfig successfully loads config`() {
        val playerCountProperty = "playerCount=3"
        val symbolsProperty = "symbols=A,B,C"
        val inputStream = ByteArrayInputStream(("$playerCountProperty\n$symbolsProperty").toByteArray())
        val config = loadConfig(inputStream)
        assertEquals(
            config, Config(
                playerCount = 3,
                symbols = listOf("A", "B", "C"),
                reader = ConsoleReader(),
                writer = ConsoleWriter()
            )
        )
    }

    @Test
    fun `given a config with incorrect player count, loadConfig fails`() {
        val playerCountProperty = "playerCount=a"
        var checked = false
        val symbolsProperty = "symbols="
        val inputStream = ByteArrayInputStream(("$playerCountProperty\n$symbolsProperty").toByteArray())
        try {
            loadConfig(inputStream)
        } catch (ex: InvalidConfigException) {
            assertEquals(ex.message, "playerCount can only be a number")
            checked = true

        }
        assert(checked)
    }

    @Test
    fun `given a config where playerCount and number of symbols don't match, validateConfig fails`() {
        var checked = false
        val invalidConfig = Config(
            playerCount = 3,
            symbols = listOf("A"),
            reader = ConsoleReader(),
            writer = ConsoleWriter()
        )
        try {
            validateConfig(invalidConfig)
        } catch (ex: InvalidConfigException) {
            assertEquals(ex.message, "playerCount and number of symbols should be same")
            checked = true

        }
        assert(checked)
    }

    @Test
    fun `given a config where playerCount is less than 3, validateConfig fails`() {
        var checked = false
        val invalidConfig = Config(
            playerCount = 2,
            symbols = listOf("A","B"),
            reader = ConsoleReader(),
            writer = ConsoleWriter()
        )
        try {
            validateConfig(invalidConfig)
        } catch (ex: InvalidConfigException) {
            assertEquals(ex.message, "playerCount cannot be less than 3")
            checked = true

        }
        assert(checked)
    }
}