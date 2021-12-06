package net.hobitin.aoc2021.day2

class Submarine {

  private var depth = 0L
  private var position = 0L

  def process(input: Array[String]): Submarine = {
    input match {
      case Array("forward", number) => position += number.toInt
      case Array("down", number) => depth += number.toInt
      case Array("up", number) => depth -= number.toInt
    }

    this
  }

  def report: Long = depth * position

}
