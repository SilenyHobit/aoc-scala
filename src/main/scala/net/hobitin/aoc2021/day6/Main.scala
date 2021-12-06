package net.hobitin.aoc2021.day6

import net.hobitin.TaskMixin

object Main extends TaskMixin{
  def main(args: Array[String]): Unit = {
    val fish = SchoolOfFish.build(input().head)

    (0 until 80).foreach(_ => fish.nextDay())

    printFirst(fish.count())

    (0 until 176).foreach(_ => fish.nextDay())

    printSecond(fish.count())
  }
}
