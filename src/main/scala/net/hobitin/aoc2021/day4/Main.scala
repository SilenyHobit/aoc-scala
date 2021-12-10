package net.hobitin.aoc2021.day4

import net.hobitin.TaskMixin

object Main extends TaskMixin {
  def main(args: Array[String]): Unit = {
    val inputRows = input
    val calledNumbers = inputRows.head
      .split(",")
      .map(_.toInt)
      .toSeq

    val boards = calledNumbers.foldLeft(
      Boards(inputRows.tail
        .grouped(6)
        .map(_.tail)
        .map(Board(_))
        .toSeq
      )
    )(_.mark(_))

    printFirst(boards.firstSum)
    printSecond(boards.lastSum)
  }
}
