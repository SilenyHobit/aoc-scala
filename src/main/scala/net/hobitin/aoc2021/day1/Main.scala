package net.hobitin.aoc2021.day1

import net.hobitin.TaskMixin

object Main extends TaskMixin {
  def main(args: Array[String]): Unit = {
    val increasing = input
      .map(_.toInt)
      .sliding(2)
      .count(seq => seq.head < seq.tail.head)

    printFirst(increasing)

    val increasingWindows = input
      .map(_.toInt)
      .sliding(3)
      .map(_.sum)
      .sliding(2)
      .count(seq => seq.head < seq.tail.head)

    printSecond(increasingWindows)
  }
}
