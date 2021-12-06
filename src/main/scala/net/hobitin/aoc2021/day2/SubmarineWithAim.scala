package net.hobitin.aoc2021.day2

class SubmarineWithAim {
  private var depth = 0L
  private var position = 0L
  private var aim = 0;

  def process(input: Array[String]): SubmarineWithAim = {
    input match {
      case Array("forward", number) => {
        position += number.toInt
        depth += aim * number.toInt
      }
      case Array("down", number) => aim += number.toInt
      case Array("up", number) => aim -= number.toInt
    }

    this
  }

  def report: Long = depth * position
}
