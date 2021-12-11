package net.hobitin.aoc2021.day8

import net.hobitin.Day

object Day8 extends Day {


  override def name: String = "Day 8: Seven Segment Search"

  override def firstTask: Any =
    input
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

  override def secondTask: Any =
    input
      .map(Decoder.decode)
      .sum

}
