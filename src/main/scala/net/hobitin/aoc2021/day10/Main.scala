package net.hobitin.aoc2021.day10

import net.hobitin.TaskMixin

object Main extends TaskMixin {
  def main(args: Array[String]): Unit = {
    val parsers = input
      .map(new Parser(_))

    printFirst(
      parsers
        .map(_.corruptScore)
        .groupBy(identity)
        .map(tuple => tuple._1 * tuple._2.length)
        .sum
    )

    val autocompleteScores = parsers
      .filter(!_.isCorrupted)
      .map(_.autocompleteScore)
      .sorted

    printSecond(autocompleteScores(autocompleteScores.length / 2))
  }
}
