package tictactoe

import tictactoe.io.Writer

class Game(private val players: List<Player>) {

    private lateinit var winner : Player

    fun numberOfPlayers(): Int {
        return players.size
    }

    fun printPlayerDetails(writer: Writer) {
        writer.println("Player Details :")
        players.forEach { player ->
            writer.println("[Player ID: ${player.id}, Player Symbol: ${player.symbol}]")
        }
    }

    fun getWinner(): Player {
        return winner
    }

    fun isOver(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun nextPlayer(): Player {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun printCurrentState(output: Writer) {
        output.println("This is what game looks like")
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun makeMove(move: Move) {

    }
}