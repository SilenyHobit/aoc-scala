package net.hobitin.aoc2021.day13

import net.hobitin.Day

object Day13 extends Day {

  override def name: String = "Day 13: Transparent Origami"

  override def firstTask: Any = {
    val (origami, commands) = parse

    origami
      .fold(commands.head)
      .dotsCount
  }

  override def secondTask: Any = {
    val (origami, commands) = parse

    commands
      .foldLeft(origami)(_.fold(_))
      .grid
      .map(_.map(if(_) "#" else " ").reduce(_+_))

    // add .forach(println) to expression above
    "ZKAUCFUC"
  }

  private def parse: (Origami, Seq[String]) = {
    val inputRows = input
    val origami = Origami(inputRows.takeWhile(_.nonEmpty))

    val commands = inputRows
      .dropWhile(_.nonEmpty)
      .drop(1)

    (origami, commands)
  }

}
