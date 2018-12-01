package tictactoe

import tictactoe.exception.InvalidConfigException

class Engine(config: Config) {

    private lateinit var game: Game

    private fun initialize() {
        val players = loadPlayers()
        game = Game(players)
    }

    fun startGame() {
        initialize()
        welcomeAndInform()
        runGame()
    }

    private val writer = config.writer
    private val playerCount = config.playerCount
    private val symbols = config.symbols

    private fun runGame() {
        game.initialize()
        writer.println("Current State :")
        game.printCurrentState(writer)
        /*while (!game.isOver()) {
            val player = game.nextPlayer()
            when (player) {
                is Human -> game.makeMove(askMove(player))
                is Bot -> game.makeMove(player.nextMove())
            }
            game.printCurrentState(writer)
        }
        writer.println("Congratulations Player:${game.getWinner()} has won the game")*/
    }

    private fun welcomeAndInform() {
        writer.println("Welcome to Tic Tac Toe")
        writer.println("Number of Players : $playerCount")
        game.printPlayerDetails(writer)
        writer.println("Game Begins Now")
    }

    private fun loadPlayers(): List<Player> {
        if (playerCount != symbols.size || playerCount < 3)
            throw InvalidConfigException("Number of symbols and players not equal")

        val players = mutableListOf<Player>()
        for (index in 1 until playerCount) {
            players.add(Player(id = "Human-$index", symbol = symbols[index - 1]))
        }
        players.add(Player(id = "Bot", symbol = symbols[playerCount - 1]))
        return players
    }

    /*private fun askMove(player: Player): Move {
        writer.println("Next move for Player:${player.id}")
        val moveString = reader.readLine()
        val move: Move
        try {
            move = InputProcessor.parse(moveString)
        } catch (e: ParseException) {
            writer.println("Invalid reader, try again")
            return askMove(player)
        }
        return move
    }*/

}






