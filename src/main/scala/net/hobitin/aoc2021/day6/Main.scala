package net.hobitin.aoc2021.day6

import net.hobitin.TaskMixin

object Main extends TaskMixin{
  def main(args: Array[String]): Unit = {
    val fish = SchoolOfFish(input.head)

    for (_ <- 0 until 80) fish.nextDay()

    printFirst(fish.count)

    for (_ <- 0 until 176) fish.nextDay()

    printSecond(fish.count)
  }
}
