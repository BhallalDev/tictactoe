package tictactoe

import tictactoe.exception.InvalidMoveException

class Engine(config: Config) {

    private lateinit var game: Game
    private val writer = config.writer
    private val reader = config.reader
    private val playerCount = config.playerCount
    private val symbols = config.symbols

    private fun initialize() {
        val players = loadPlayers()
        game = Game(players)
    }

    fun startGame() {
        initialize()
        welcomeAndInform()
        runGame()
    }

    private fun runGame() {
        game.initialize()
        writer.println("Current State :")
        game.printCurrentState(writer)
        while (!game.isOver()) {
            val player = game.nextPlayer()
            when (player) {
                is Human -> {
                    val move = askMove(player)
                    game.makeMove(move)
                }
                is Bot -> {
                    writer.println("Computer playing now :")
                    val move = player.nextMove(game)
                    game.makeMove(move)
                }
            }
            game.printCurrentState(writer)
        }
        if (game.getWinner() != null)
            writer.println("Congratulations Player: ${game.getWinner()} has won the game")
        else
            writer.println("Game raw")
    }

    private fun welcomeAndInform() {
        writer.println("Welcome to Tic Tac Toe")
        writer.println("Number of Players : $playerCount")
        game.printPlayerDetails(writer)
        writer.println("Game Begins Now")
    }

    private fun loadPlayers(): List<Player> {
        val players = mutableListOf<Player>()
        for (index in 1 until playerCount) {
            players.add(Human(id = "Human-$index", symbol = symbols[index - 1]))
        }
        players.add(Bot(id = "Bot", symbol = symbols[playerCount - 1]))
        return players
    }

    private fun askMove(player: Player): Move {
        writer.println("Next move for Player:${player.id}")
        val moveString = reader.readLine()
        val move: Move
        try {
            val position = InputProcessor.parse(moveString)
            move = Move(player, position)
            game.validateMove(move)
        } catch (e: ParseException) {
            writer.println("Invalid input, try again")
            return askMove(player)
        } catch (e: InvalidMoveException) {
            writer.println(e.message!!)
            return askMove(player)
        }
        return move
    }

}








