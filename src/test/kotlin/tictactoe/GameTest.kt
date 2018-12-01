package tictactoe

import org.junit.Assert.assertEquals
import org.junit.Test
import tictactoe.exception.DuplicateMoveException
import tictactoe.exception.InvalidMoveException

class GameTest {

    private val writer = TestWriter()

    private val playerOne = Player("1", "X")
    private val players = listOf(
        playerOne,
        Player("2", "Y"),
        Player("3", "Z")
    )
    @Test
    fun `given players, should print player details`() {
        val game = Game(players)
        game.printPlayerDetails(writer)
        assertEquals("[Player ID: 1, Player Symbol: X]",writer.lines[1])
        assertEquals("[Player ID: 2, Player Symbol: Y]",writer.lines[2])
        assertEquals("[Player ID: 3, Player Symbol: Z]",writer.lines[3])
    }

    @Test
    fun `given no move recorded, should print empty game state`(){
        val game = Game(players)
        game.initialize()
        game.printCurrentState(writer)
        assertEquals("_ _ _", writer.lines[0].trim())
        assertEquals("_ _ _", writer.lines[1].trim())
        assertEquals("_ _ _", writer.lines[2].trim())
    }

    @Test
    fun `given a valid move is made, move is shown in current state`() {
        val game = Game(players)
        game.initialize()
        val move = Move(
            position = Position(0,0),
            player = playerOne
        )
        game.makeMove(move)
        game.printCurrentState(writer)
        assertEquals("X _ _", writer.lines[0].trim())
        assertEquals("_ _ _", writer.lines[1].trim())
        assertEquals("_ _ _", writer.lines[2].trim())

    }

    @Test(expected = InvalidMoveException::class)
    fun `given a move is made outside boundaries, it fails`(){
        val game = Game(players)
        game.initialize()
        val move = Move(
            position = Position(4,4),
            player = playerOne
        )
        game.makeMove(move)
    }
    @Test(expected = DuplicateMoveException::class)
    fun `given a duplicate move is made, it fails`(){
        val game = Game(players)
        game.initialize()
        val move = Move(
            position = Position(0,0),
            player = playerOne
        )
        game.makeMove(move)
        game.makeMove(move)
    }

}