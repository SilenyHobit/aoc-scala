package net.hobitin.aoc2021.day11

import net.hobitin.TaskMixin

import scala.annotation.tailrec

object Main extends TaskMixin{
  def main(args: Array[String]): Unit = {
    val octopuses = (0 until 100)
      .foldLeft(Octopuses(input))((octopuses, _) => octopuses.step())

    printFirst(octopuses.flashes)

    printSecond(findAllFlash(octopuses))
  }

  @tailrec
  private def findAllFlash(octopuses: Octopuses): Int = {
    if (octopuses.allFlashedOn.isDefined) octopuses.allFlashedOn.get
    else findAllFlash(octopuses.step())
  }
}
