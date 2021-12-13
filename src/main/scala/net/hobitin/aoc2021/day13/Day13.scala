package net.hobitin.aoc2021.day13

import net.hobitin.Day

object Day13 extends Day {

  override def name: String = "Day 13: Transparent Origami"

  override def firstTask: Any = {
    val origami = Origami(input.takeWhile(_.nonEmpty))

    val commands = input
      .dropWhile(_.nonEmpty)
      .drop(1)

    origami
      .fold(commands.head)
      .dotsCount
  }

  override def secondTask: Any = {
    val origami = Origami(input.takeWhile(_.nonEmpty))

    val commands = input
      .dropWhile(_.nonEmpty)
      .drop(1)

    commands
      .foldLeft(origami)(_.fold(_))
      .grid
      .map(array => array.map(if(_) "#" else "_").reduce(_+_))

    // add .forach(println) to expression above
    "ZKAUCFUC"
  }

}
