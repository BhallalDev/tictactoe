package tictactoe

import tictactoe.exception.DuplicateMoveException
import tictactoe.exception.InvalidMoveException
import tictactoe.io.Writer

class Game(private val players: List<Player>) {

    private lateinit var state: Array<Array<Player?>>

    private lateinit var winner: Player

    fun initialize() {
        state = arrayOf(
            arrayOfNulls(3),
            arrayOfNulls(3),
            arrayOfNulls(3)
        )
    }

    fun printPlayerDetails(writer: Writer) {
        writer.println("Player Details :")
        players.forEach { player ->
            writer.println("[Player ID: ${player.id}, Player Symbol: ${player.symbol}]")
        }
    }

    fun printCurrentState(output: Writer) {
        state.forEach { row ->
            row.forEach { player ->
                val symbol = player?.symbol ?: "_"
                output.print("$symbol ")
            }
            output.println("")
        }
    }

    fun makeMove(move: Move) {
        validateMove(move)
        state[move.position.row][move.position.column] = move.player
    }

    private fun validateMove(move: Move) {
        val position = move.position
        if ((position.row > players.size - 1) || (position.row > players.size - 1))
            throw InvalidMoveException("Move outside of boundaries")
        if (state[move.position.row][move.position.column] != null)
            throw DuplicateMoveException("Move already made")
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
}