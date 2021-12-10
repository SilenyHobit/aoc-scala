package net.hobitin.aoc2021.day10

import net.hobitin.TaskMixin

object Main extends TaskMixin {
  def main(args: Array[String]): Unit = {
    val corrupt = input
      .map(new Parser(_))
      .map(_.corruptScore)
      .filter(_ != 0)
      .groupBy(identity)
      .map(tuple => tuple._1*tuple._2.length)
      .sum

    printFirst(corrupt)

    val autocompleteScores = input
      .map(new Parser(_))
      .filter(!_.isCorrupted)
      .map(_.autocompleteScore)
      .sorted

    printSecond(autocompleteScores(autocompleteScores.length/2))
  }
}
