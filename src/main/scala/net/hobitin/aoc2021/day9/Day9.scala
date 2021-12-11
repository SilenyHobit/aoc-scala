package net.hobitin.aoc2021.day9

import net.hobitin.Day

object Day9 extends Day {


  override def name: String = "Day 9: Smoke Basin"

  override def firstTask: Any = Vents(input).riskLevel

  override def secondTask: Any = Vents(input).basins

}
