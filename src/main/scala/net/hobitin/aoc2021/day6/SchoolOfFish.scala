package net.hobitin.aoc2021.day6

class SchoolOfFish private(private val fish: Array[Long]) {
  def nextDay(): SchoolOfFish =
    new SchoolOfFish(Array(fish(1), fish(2), fish(3), fish(4), fish(5), fish(6), fish(7) + fish(0), fish(8), fish(0)))

  def count: Long = fish.sum
}

object SchoolOfFish {
  def apply(input: String): SchoolOfFish = {
    val fish = new Array[Long](9)
    input
      .split(",")
      .map(_.toInt)
      .foreach(fish(_) += 1)

    new SchoolOfFish(fish)
  }
}
