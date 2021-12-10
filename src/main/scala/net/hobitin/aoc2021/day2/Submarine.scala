package net.hobitin.aoc2021.day2

class Submarine private(private val depth: Int, private val position: Int) {

  def process(input: Array[String]): Submarine = {
    input match {
      case Array("forward", number) => new Submarine(depth, position + number.toInt)
      case Array("down", number) => new Submarine(depth + number.toInt, position)
      case Array("up", number) => new Submarine(depth - number.toInt, position)
    }
  }

  def report: Long = depth * position
}

object Submarine {
  def apply(): Submarine = new Submarine(0, 0)
}
