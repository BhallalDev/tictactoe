package tictactoe

import org.junit.Assert.*
import org.junit.Test
import tictactoe.exception.DuplicateMoveException
import tictactoe.exception.InvalidMoveException

class GameTest {

    private val writer = TestWriter()

    private val playerOne = Player("1", "X")
    private val playerTwo = Player("2", "Y")
    private val playerThree = Player("3", "Z")

    private val players = listOf(
        playerOne,
        playerTwo,
        playerThree
    )

    @Test
    fun `given players, should print player details`() {
        val game = Game(players)
        game.printPlayerDetails(writer)
        assertEquals("[Player ID: 1, Player Symbol: X]", writer.lines[1])
        assertEquals("[Player ID: 2, Player Symbol: Y]", writer.lines[2])
        assertEquals("[Player ID: 3, Player Symbol: Z]", writer.lines[3])
    }

    @Test
    fun `given no move recorded, should print empty game state`() {
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
            position = Position(0, 0),
            player = playerOne
        )
        game.makeMove(move)
        game.printCurrentState(writer)
        assertEquals("X _ _", writer.lines[0].trim())
        assertEquals("_ _ _", writer.lines[1].trim())
        assertEquals("_ _ _", writer.lines[2].trim())

    }

    @Test(expected = InvalidMoveException::class)
    fun `given a move is made outside boundaries, it fails`() {
        val game = Game(players)
        game.initialize()
        val move = Move(
            position = Position(4, 4),
            player = playerOne
        )
        game.makeMove(move)
    }

    @Test
    fun `given  all places are filled, isOver returns true`() {
        val game = Game(players)
        game.initialize()
        game.makeMove(Move(position = Position(0, 0), player = playerOne))
        game.makeMove(Move(position = Position(0, 1), player = playerTwo))
        game.makeMove(Move(position = Position(0, 2), player = playerThree))
        game.makeMove(Move(position = Position(1, 0), player = playerTwo))
        game.makeMove(Move(position = Position(1, 1), player = playerOne))
        game.makeMove(Move(position = Position(1, 2), player = playerThree))
        game.makeMove(Move(position = Position(2, 0), player = playerThree))
        game.makeMove(Move(position = Position(2, 1), player = playerOne))
        game.makeMove(Move(position = Position(2, 2), player = playerTwo))
        assertTrue(game.isOver())
    }

    @Test
    fun `given a row is filled by player, isOver returns true, and player becomes winner`() {
        val game = Game(players)
        game.initialize()
        game.makeMove(Move(position = Position(1, 0), player = playerOne))
        game.makeMove(Move(position = Position(1, 1), player = playerOne))
        game.makeMove(Move(position = Position(1, 2), player = playerOne))
        assertTrue(game.isOver())
        assertEquals(playerOne,game.getWinner())
    }

    @Test
    fun `given a column is filled by player, isOver returns true, and player becomes winner`() {
        val game = Game(players)
        game.initialize()
        game.makeMove(Move(position = Position(0, 1), player = playerOne))
        game.makeMove(Move(position = Position(1, 1), player = playerOne))
        game.makeMove(Move(position = Position(2, 1), player = playerOne))
        assertTrue(game.isOver())
        assertEquals(playerOne,game.getWinner())
    }

    @Test
    fun `given a top left to bottom right diagonal is filled by player, isOver returns true, and player becomes winner`() {
        val game = Game(players)
        game.initialize()
        game.makeMove(Move(position = Position(0, 0), player = playerOne))
        game.makeMove(Move(position = Position(1, 1), player = playerOne))
        game.makeMove(Move(position = Position(2, 2), player = playerOne))
        assertTrue(game.isOver())
        assertEquals(playerOne,game.getWinner())
    }

    @Test
    fun `given a bottom left to top right diagonal is filled by player, isOver returns true, and player becomes winner`() {
        val game = Game(players)
        game.initialize()
        game.makeMove(Move(position = Position(2, 0), player = playerOne))
        game.makeMove(Move(position = Position(1, 1), player = playerOne))
        game.makeMove(Move(position = Position(0, 2), player = playerOne))
        assertTrue(game.isOver())
        assertEquals(playerOne,game.getWinner())
    }

    @Test(expected = DuplicateMoveException::class)
    fun `given a duplicate move is made, it fails`() {
        val game = Game(players)
        game.initialize()
        val move = Move(
            position = Position(0, 0),
            player = playerOne
        )
        game.makeMove(move)
        game.makeMove(move)
    }

}