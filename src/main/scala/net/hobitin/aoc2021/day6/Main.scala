package net.hobitin.aoc2021.day6

import net.hobitin.TaskMixin

object Main extends TaskMixin {
  def main(args: Array[String]): Unit = {
    printFirst(
      (0 until 80)
        .foldLeft(SchoolOfFish(input.head))((schoolOfFish, _) => schoolOfFish.nextDay())
        .count
    )

    printSecond(
      (0 until 256)
        .foldLeft(SchoolOfFish(input.head))((schoolOfFish, _) => schoolOfFish.nextDay())
        .count
    )
  }
}
