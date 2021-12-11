package net.hobitin.aoc2021.day11

import net.hobitin.Day

import scala.annotation.tailrec

object Day11 extends Day {

  override def name: String = "Day 11: Dumbo Octopus"

  override def firstTask: Any =
    (0 until 100)
      .foldLeft(Octopuses(input))((octopuses, _) => octopuses.step())
      .flashes

  override def secondTask: Any = findAllFlash(Octopuses(input))

  @tailrec
  private def findAllFlash(octopuses: Octopuses): Int = {
    if (octopuses.allFlashedOn.isDefined) octopuses.allFlashedOn.get
    else findAllFlash(octopuses.step())
  }

}
