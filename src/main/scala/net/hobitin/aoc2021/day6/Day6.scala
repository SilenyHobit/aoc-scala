package net.hobitin.aoc2021.day6

import net.hobitin.Day

object Day6 extends Day {

  override def name: String = "Day 6: Lanternfish"

  override def firstTask: Any =
    (0 until 80)
      .foldLeft(SchoolOfFish(input.head))((schoolOfFish, _) => schoolOfFish.nextDay())
      .count

  override def secondTask: Any =
    (0 until 256)
      .foldLeft(SchoolOfFish(input.head))((schoolOfFish, _) => schoolOfFish.nextDay())
      .count

}
