package net.hobitin.aoc2021

import net.hobitin.aoc2021.day1.Day1
import net.hobitin.aoc2021.day10.Day10
import net.hobitin.aoc2021.day11.Day11
import net.hobitin.aoc2021.day2.Day2
import net.hobitin.aoc2021.day3.Day3
import net.hobitin.aoc2021.day4.Day4
import net.hobitin.aoc2021.day5.Day5
import net.hobitin.aoc2021.day6.Day6
import net.hobitin.aoc2021.day7.Day7
import net.hobitin.aoc2021.day8.Day8
import net.hobitin.aoc2021.day9.Day9

object RunAllDays {

  private val days = Seq(Day1, Day2, Day3, Day4, Day5, Day6, Day7, Day8, Day9, Day10, Day11)

  def main(args: Array[String]): Unit = {
    println("Timing of different parts if off by a lot due to OS caching of the input files.")
    println("-----------------------------------------------------------------")
    val overallDuration = days
      .map(RunSingleDay.runSingleDay)
      .reduce(_.plus(_))

    println(f"${overallDuration.toSecondsPart}%58d.${overallDuration.toMillisPart}%03dS")
  }

}
