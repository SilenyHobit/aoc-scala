package net.hobitin.aoc2021

import net.hobitin.Day
import net.hobitin.aoc2021.day15.Day15

import java.time.{Duration, Instant}

object RunSingleDay {

  private val day = Day15

  def main(args: Array[String]): Unit = {
    println("-----------------------------------------------------------------------")
    runSingleDay(day)
  }

  def runSingleDay(day: Day, runs: Int = 1): Duration = {
    //warmup
    day.firstTask
    day.secondTask

    val result = (0 until runs)
      .map(_ => {
        val firstStart = Instant.now()
        val firstResult = day.firstTask
        val firstDuration = Duration.between(firstStart, Instant.now())

        val secondStart = Instant.now()
        val secondResult = day.secondTask
        val secondDuration = Duration.between(secondStart, Instant.now())

        (firstResult, secondResult, firstDuration.toNanos, secondDuration.toNanos)
      })
      .reduce((tuple1, tuple2) => (tuple1._1, tuple1._2, tuple1._3 + tuple2._3, tuple1._4 + tuple2._4))

    val firstDuration = Duration.ofNanos(result._3 / runs)

    val secondDuration = Duration.ofNanos(result._4 / runs)

    val duration = firstDuration.plus(secondDuration)

    println(f"| ${day.name}%-35s | ${duration.toSecondsPart}%21d.${duration.toNanosPart / 1000}%06dS |")
    println(f"| ${"    Part 1"}%-35s | ${result._1}%15s | ${firstDuration.toSecondsPart}%3d.${firstDuration.toNanosPart / 1000}%06dS |")
    println(f"| ${"    Part 2"}%-35s | ${result._2}%15s | ${secondDuration.toSecondsPart}%3d.${secondDuration.toNanosPart / 1000}%06dS |")
    println("-----------------------------------------------------------------------")

    duration
  }

}
