package net.hobitin.aoc2021.day1

import net.hobitin.Day

object Day1 extends Day {

  override def name: String = "Day 1: Sonar Sweep"

  override def firstTask: Any =
    input
      .map(_.toInt)
      .sliding(2)
      .count(seq => seq.head < seq.tail.head)

  override def secondTask: Any =
    input
      .map(_.toInt)
      .sliding(3)
      .map(_.sum)
      .sliding(2)
      .count(seq => seq.head < seq.tail.head)

}
