package net.hobitin.aoc2021.day1

import net.hobitin.TaskMixin

object Main extends TaskMixin {
  def main(args: Array[String]): Unit = {
    val increasing = input().map(_.toInt)
      .sliding(2)
      .map(seq => if (seq.head < seq.tail.head) 1 else 0)
      .sum

    printFirst(increasing)

    val increasingWindows = input()
      .map(_.toInt)
      .sliding(3)
      .map(_.sum)
      .sliding(2)
      .map(seq => if (seq.head < seq.tail.head) 1 else 0)
      .sum

    printSecond(increasingWindows)
  }
}
