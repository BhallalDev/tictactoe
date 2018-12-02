import tictactoe.Config
import tictactoe.Engine
import tictactoe.exception.InvalidConfigException
import tictactoe.io.ConsoleReader
import tictactoe.io.ConsoleWriter
import java.io.File
import java.io.InputStream
import java.util.*

fun main(args: Array<String>) {
    val configFile = File("config.properties")
    val config: Config

    try {
        config = loadConfig(configFile.inputStream())
        validateConfig(config)
    } catch (ex: NumberFormatException) {
        println("Symbols must be a comma separated string ex : A,B,C")
        return
    } catch (ex: InvalidConfigException) {
        println(ex.message)
        return
    }

    val engine = Engine(config)
    engine.startGame()
}

fun validateConfig(config: Config) {
    if((config.playerCount != config.symbols.size) && config.playerCount >= 3){
        throw InvalidConfigException("playerCount and number of symbols should be same")
    }
}

fun loadConfig(inputStream: InputStream): Config {
    val prop = Properties()
    prop.load(inputStream)
    val playerCountString = prop.getProperty("playerCount")
    val playerCount: Int
    try {
        playerCount = Integer.parseInt(playerCountString)
    } catch (ex: NumberFormatException) {
        throw InvalidConfigException("playerCount can only be a number")
    }
    val symbols = stringToList(prop.getProperty("symbols"))
    return Config(
        playerCount = playerCount,
        symbols = symbols,
        reader = ConsoleReader(),
        writer = ConsoleWriter()
    )
}

private fun stringToList(symbolsString: String): List<String> {
    val symbols = mutableListOf<String>()
    symbolsString.split(",").forEach { symbol ->
        symbols.add(symbol)
    }
    return symbols
}




