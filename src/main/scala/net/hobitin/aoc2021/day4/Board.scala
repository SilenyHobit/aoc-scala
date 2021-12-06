package net.hobitin.aoc2021.day4

class Board private(private val board: Array[Array[Int]]) {

  def mark(number: Int): Board = {
    val newBoard = board.map(row => {
      row.map(value => if (value == number) -1 else value)
    })
    new Board(newBoard)
  }

  def isFinished: Boolean = (0 until 5)
    .map(index => checkRow(index) || checkColumn(index))
    .reduce(_ || _)

  private def checkRow(row: Int): Boolean = !board(row).exists(_ != -1)

  private def checkColumn(column: Int): Boolean = !(0 until 5).exists(index => board(index)(column) != -1)

  def sum: Int = board
    .map(array => array.filter(_ != -1).sum)
    .sum

}

object Board {
  def apply(board: Seq[String]): Board = new Board(board.map(value => {
      value.split(" ")
        .filter(value => value.nonEmpty)
        .map(_.toInt)
    }).toArray)
}
