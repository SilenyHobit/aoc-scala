package net.hobitin.aoc2021.day7

import net.hobitin.Day

object Day7 extends Day{


  override def name: String = "Day 7: The Treachery of Whales"

  override def firstTask: Any = {
    val cachedCrabs = crabs
    sumFuel(cachedCrabs(cachedCrabs.length/2), cachedCrabs)
  }

  override def secondTask: Any = {
    val cachedCrabs = crabs
    val averageCrab = cachedCrabs.sum / cachedCrabs.length

    Seq(averageCrab - 1, averageCrab, averageCrab + 1).map(sumFuel2(_, cachedCrabs)).min
  }

  private def crabs: Seq[Int] =
    input.head
      .split(",")
      .map(_.toInt)
      .sorted
      .toSeq

  private def sumFuel(chosen: Int, crabs: Seq[Int]): Int = crabs.map(crab => Math.abs(chosen-crab)).sum

  private def sumFuel2(chosen: Int, crabs: Seq[Int]): Long = crabs.map(crab => Math.abs(chosen-crab)).map(distance => (distance*(distance+1))/2).sum

}
