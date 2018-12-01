package tictactoe

import tictactoe.io.Reader
import tictactoe.io.Writer


class Config( val playerCount: Int,
              val symbols: List<String>,
              val reader: Reader,
              val writer: Writer)