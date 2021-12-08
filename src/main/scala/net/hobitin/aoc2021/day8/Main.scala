package net.hobitin.aoc2021.day8

import net.hobitin.TaskMixin

object Main extends TaskMixin {
  def main(args: Array[String]): Unit = {
    val outputCount = input
      .map(_.split("\\|"))
      .map(_.tail.head)
      .flatMap(_.split(" "))
      .map(_.length)
      .count(value => {
        value match {
          case 2 | 3 | 4 | 7 => true
          case _ => false
        }
      })

    printFirst(outputCount)

    val sum = input
      .map(Decoder.decode)
      .sum

    printSecond(sum)
  }
}
