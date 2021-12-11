package net.hobitin.aoc2021.day4

import net.hobitin.Day

object Day4 extends Day {


  override def name: String = "Day 4: Giant Squid"

  override def firstTask: Any = load.firstSum

  override def secondTask: Any = load.lastSum

  private def load: Boards = {
    val inputRows = input
    val calledNumbers = inputRows.head
      .split(",")
      .map(_.toInt)
      .toSeq

    calledNumbers.foldLeft(
      Boards(inputRows.tail
        .grouped(6)
        .map(_.tail)
        .map(Board(_))
        .toSeq
      )
    )(_.mark(_))
  }

}
