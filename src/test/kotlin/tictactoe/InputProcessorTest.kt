package tictactoe

import org.junit.Assert.assertEquals
import org.junit.Test

class InputProcessorTest{

    @Test
    fun `should parse valid move`(){
        val position = InputProcessor.parse("1,2")
        assertEquals(Position(1,2),position)
    }

    @Test(expected = ParseException::class)
    fun `should fail when no comma`() {
        InputProcessor.parse("1")
    }

    @Test(expected = ParseException::class)
    fun `should fail when not a number`() {
        InputProcessor.parse("a,b")
    }
}