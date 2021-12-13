package net.hobitin.aoc2021.day3

import net.hobitin.Day

object Day3 extends Day{

  override def name: String = "Day 3: Binary Diagnostic"

  override def firstTask: Any = {
    val binaryRates = input.foldLeft(new BinaryRates)(_.process(_))

    binaryRates.gammaRate * binaryRates.epsilonRate
  }

  override def secondTask: Any = {
    val binaryRates = input.foldLeft(new BinaryRates)(_.process(_))

    binaryRates.findOxygenRating(input) * binaryRates.findScrubberRating(input)
  }

}
