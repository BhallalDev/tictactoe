package tictactoe

open class Player(val id: String, val symbol: String) {
    override fun toString(): String {
        return "Player(id='$id', symbol='$symbol')"
    }
}

class Bot(id: String, symbol: String) : Player(id, symbol) {
    fun nextMove(game: Game): Move {
        val freePositions = game.freePositions()
        val randomPosition = (0 until freePositions.size).random()
        return Move(this, freePositions[randomPosition])
    }
}

class Human(id: String, symbol: String) : Player(id, symbol)


