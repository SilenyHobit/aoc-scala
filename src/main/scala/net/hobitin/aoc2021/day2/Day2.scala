package net.hobitin.aoc2021.day2

import net.hobitin.Day

object Day2 extends Day {


  override def name: String = "Day 2: Dive!"

  override def firstTask: Any =
    input
      .map(_.split(" "))
      .foldLeft(Submarine())((submarine, array) => submarine.process(array))
      .report

  override def secondTask: Any =
    input
      .map(_.split(" "))
      .foldLeft(SubmarineWithAim())((submarine, array) => submarine.process(array))
      .report

}
