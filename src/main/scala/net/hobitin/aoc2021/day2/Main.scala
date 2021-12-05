package net.hobitin.aoc2021.day2

import net.hobitin.TaskMixin

object Main extends TaskMixin {
  def main(args: Array[String]): Unit = {

    val first = input()
      .map(_.split(" "))
      .foldLeft(new Submarine)((submarine, array) => submarine.process(array))
      .report()

    printFirst(first)

    val second = input()
      .map(_.split(" "))
      .foldLeft(new SubmarineWithAim)((submarine, array) => submarine.process(array))
      .report()

    printFirst(second)
  }
}
