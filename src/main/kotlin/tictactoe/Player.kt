package tictactoe

open class Player(val id: String, val symbol: String)



class Bot(id: String, symbol: String) : Player(id, symbol) {
    fun nextMove(): Move {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}


class Human(id: String, symbol: String) : Player(id, symbol)


