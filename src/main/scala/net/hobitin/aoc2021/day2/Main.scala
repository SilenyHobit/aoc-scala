package net.hobitin.aoc2021.day2

import net.hobitin.TaskMixin

object Main extends TaskMixin {
  def main(args: Array[String]): Unit = {
    printFirst(
      input
        .map(_.split(" "))
        .foldLeft(Submarine())((submarine, array) => submarine.process(array))
        .report
    )

    printFirst(
      input
        .map(_.split(" "))
        .foldLeft(SubmarineWithAim())((submarine, array) => submarine.process(array))
        .report
    )
  }
}
