package tictactoe

data class Move (var player: Player, val position: Position)

data class Position (val row : Int, val column: Int )