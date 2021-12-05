package net.hobitin.aoc2021.day3

import net.hobitin.TaskMixin

object Main extends TaskMixin{
  def main(args: Array[String]): Unit = {
    val binaryRates = input().foldLeft(new BinaryRates)((rates, value) => rates.process(value))
    printFirst(binaryRates.gammaRate() * binaryRates.epsilonRate())

    printSecond(binaryRates.findOxygenRating(input()) * binaryRates.findScrubberRating(input()))
  }
}
