package net.hobitin.aoc2021

import net.hobitin.Day
import net.hobitin.aoc2021.day12.Day12

import java.time.temporal.ChronoUnit
import java.time.{Duration, Instant}

object RunSingleDay {

  private val day = Day12

  def main(args: Array[String]): Unit = {
    println("-----------------------------------------------------------------")
    runSingleDay(day)
  }

  def runSingleDay(day: Day): Duration = {
    val firstStart = Instant.now()
    val firstResult = day.firstTask
    val firstDuration = Duration.between(firstStart, Instant.now()).truncatedTo(ChronoUnit.MILLIS)

    val secondStart = Instant.now()
    val secondResult = day.secondTask
    val secondDuration = Duration.between(secondStart, Instant.now()).truncatedTo(ChronoUnit.MILLIS)

    val duration = firstDuration.plus(secondDuration)

    println(f"| ${day.name}%-30s | ${duration.toSecondsPart}%23d.${duration.toMillisPart}%03dS |")
    println(f"| ${"    Part 1"}%-30s | ${firstResult}%15s | ${firstDuration.toSecondsPart}%5d.${firstDuration.toMillisPart}%03dS |")
    println(f"| ${"    Part 2"}%-30s | ${secondResult}%15s | ${secondDuration.toSecondsPart}%5d.${secondDuration.toMillisPart}%03dS |")
    println("-----------------------------------------------------------------")

    duration
  }

}
