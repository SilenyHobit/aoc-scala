package net.hobitin.aoc2021.day6

class SchoolOfFish private (private val fish: Array[Long]) {
  def nextDay(): Unit = {
    val finished = fish(0)
    fish(0) = fish(1)
    fish(1) = fish(2)
    fish(2) = fish(3)
    fish(3) = fish(4)
    fish(4) = fish(5)
    fish(5) = fish(6)
    fish(6) = fish(7)
    fish(7) = fish(8)

    fish(6) += finished
    fish(8) = finished
  }

  def count(): Long = fish.sum
}

object SchoolOfFish {
  def build(input: String): SchoolOfFish = {
    val fish = new Array[Long](9)
    input
      .split(",")
      .map(_.toInt)
      .foreach(i => fish(i) += 1)

    new SchoolOfFish(fish)
  }
}
