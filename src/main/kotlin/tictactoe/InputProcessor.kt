package tictactoe

class InputProcessor {
    companion object {
        fun parse(input: String?): Position {
            if (input != null) {
                val numbers = input.split(",")
                if (numbers.size == 2) {
                    val row: Int
                    val column: Int
                    try {
                        row = Integer.parseInt(numbers[0].trim())
                        column = Integer.parseInt(numbers[1].trim())

                    } catch (e: NumberFormatException) {
                        throw ParseException("Please enter numbers separated with comma")
                    }
                    return Position(row, column)
                }
            }
            throw ParseException("Invalid input")
        }
    }
}
