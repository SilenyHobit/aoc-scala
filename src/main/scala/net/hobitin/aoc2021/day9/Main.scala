package net.hobitin.aoc2021.day9

import net.hobitin.TaskMixin

object Main extends TaskMixin {
  def main(args: Array[String]): Unit = {
    val vents = Vents(input)

    printFirst(vents.riskLevel)
    printSecond(vents.basins)
  }
}
