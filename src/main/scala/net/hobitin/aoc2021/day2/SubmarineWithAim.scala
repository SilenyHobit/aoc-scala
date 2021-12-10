package net.hobitin.aoc2021.day2

class SubmarineWithAim private(private val depth: Int, private val position: Int, private val aim: Int) {

  def process(input: Array[String]): SubmarineWithAim = {
    input match {
      case Array("forward", number) => new SubmarineWithAim(depth + aim * number.toInt, position + number.toInt, aim)
      case Array("down", number) => new SubmarineWithAim(depth, position, aim + number.toInt)
      case Array("up", number) => new SubmarineWithAim(depth, position, aim - number.toInt)
    }
  }

  def report: Long = depth * position
}

object SubmarineWithAim {
  def apply(): SubmarineWithAim = new SubmarineWithAim(0, 0, 0)
}
