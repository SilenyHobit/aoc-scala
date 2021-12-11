package net.hobitin.aoc2021.day10

import net.hobitin.Day

object Day10 extends Day {


  override def name: String = "Day 10: Syntax Scoring"

  override def firstTask: Any =
    input
      .map(new Parser(_))
      .map(_.corruptScore)
      .groupBy(identity)
      .map(tuple => tuple._1 * tuple._2.length)
      .sum

  override def secondTask: Any = {
    val autocompleteScores = input
      .map(new Parser(_))
      .filter(!_.isCorrupted)
      .map(_.autocompleteScore)
      .sorted

    autocompleteScores(autocompleteScores.length / 2)
  }

}
