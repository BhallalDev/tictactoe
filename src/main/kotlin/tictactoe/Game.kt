package tictactoe

import tictactoe.exception.DuplicateMoveException
import tictactoe.exception.InvalidMoveException
import tictactoe.io.Writer

class Game(private val players: List<Player>) {

    private var currentTurn = 0

    private lateinit var state: Array<Array<Player?>>

    private var winner: Player? = null

    fun initialize() {
        state = Array<Array<Player?>>(players.size) {
            Array(players.size) {
                null
            }
        }
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

    fun getWinner(): Player? {
        return winner
    }

    fun isOver(): Boolean {
        val (rowFilled, rowWinner) = checkRows()
        if (rowFilled) {
            this.winner = rowWinner!!
            return true
        }

        val (columnFilled, columnWinner) = checkColumns()
        if (columnFilled) {
            this.winner = columnWinner!!
            return true
        }
        val (diagonalOneFilled, diagonalOneWinner) = checkDiagonalTopLeftToBottomRight()
        if (diagonalOneFilled) {
            this.winner = diagonalOneWinner!!
            return true
        }

        val (diagonalTwoFilled, diagonalTwoWinner) = checkDiagonalBottomLeftToTopRight()
        if (diagonalTwoFilled) {
            this.winner = diagonalTwoWinner!!
            return true
        }
        return allMovesMade()
    }

    private fun validateMove(move: Move) {
        val position = move.position
        if ((position.row > players.size - 1) || (position.row > players.size - 1))
            throw InvalidMoveException("Move outside of boundaries")
        if (state[move.position.row][move.position.column] != null)
            throw DuplicateMoveException("Move already made")
    }

    private fun checkRows(): Pair<Boolean, Player?> {
        state.forEach { row ->
            var tempPlayer: Player? = null
            var foundWinner = true
            for (column in 0 until row.size) {
                val player = row[column]
                if (tempPlayer == null) {
                    if (player != null)
                        tempPlayer = player
                    else
                        foundWinner = false
                } else {
                    if (tempPlayer != player)
                        foundWinner = false
                }
            }
            if (foundWinner)
                return Pair(true, tempPlayer)
        }
        return Pair(false, null)
    }

    private fun checkColumns(): Pair<Boolean, Player?> {
        for (column in 0 until players.size) {
            var foundWinner = true
            var tempPlayer: Player? = null
            state.forEach { row ->
                if (tempPlayer == null) {
                    if (row[column] != null)
                        tempPlayer = row[column]
                    else
                        foundWinner = false
                } else {
                    if (tempPlayer != row[column])
                        foundWinner = false
                }
            }
            if (foundWinner)
                return Pair(true, tempPlayer)
        }
        return Pair(false, null)
    }

    private fun checkDiagonalTopLeftToBottomRight(): Pair<Boolean, Player?> {
        var foundWinner = true
        var tempPlayer: Player? = null

        for (row in 0 until players.size) {
            for (column in 0 until players.size) {
                if (row == column) {
                    if (tempPlayer == null) {
                        if (state[row][column] != null)
                            tempPlayer = state[row][column]
                        else
                            foundWinner = false
                    } else {
                        if (tempPlayer != state[row][column])
                            foundWinner = false
                    }
                }
            }
        }

        return if (tempPlayer != null && foundWinner)
            Pair(true, tempPlayer)
        else
            Pair(false, null)
    }

    private fun checkDiagonalBottomLeftToTopRight(): Pair<Boolean, Player?> {
        var foundWinner = true
        var tempPlayer: Player? = null

        for (row in 0 until players.size) {
            for (column in 0 until players.size) {
                if (row + column == players.size - 1) {
                    if (tempPlayer == null) {
                        if (state[row][column] != null)
                            tempPlayer = state[row][column]
                        else
                            foundWinner = false
                    } else {
                        if (tempPlayer != state[row][column])
                            foundWinner = false
                    }
                }
            }


        }
        if (tempPlayer != null && foundWinner)
            return Pair(true, tempPlayer)
        return Pair(false, null)
    }

    private fun allMovesMade(): Boolean {
        state.forEach { row ->
            row.forEach { column ->
                if (column == null)
                    return false
            }
        }
        return true
    }

    fun nextPlayer(): Player {
        if (currentTurn == players.size) {
            currentTurn = 0
        }
        return players[currentTurn++]
    }

    fun freePositions(): List<Position> {
        val freePositions = mutableListOf<Position>()
        for (row in 0 until players.size) {
            for (column in 0 until players.size) {
                if (state[row][column] == null)
                    freePositions.add(Position(row, column))
            }
        }
        return freePositions
    }
}