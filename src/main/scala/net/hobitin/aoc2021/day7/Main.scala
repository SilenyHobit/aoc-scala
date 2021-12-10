package net.hobitin.aoc2021.day7

import net.hobitin.TaskMixin

object Main extends TaskMixin{

  def main(args:Array[String]): Unit = {
    val crabs = input.head
      .split(",")
      .map(_.toInt)
      .sorted
      .toSeq

    printFirst(sumFuel(crabs(crabs.length/2), crabs))

    printSecond((0 to 1200).map(sumFuel2(_, crabs)).min)
  }

  def sumFuel(chosen: Int, crabs: Seq[Int]): Int = crabs.map(crab => Math.abs(chosen-crab)).sum

  def sumFuel2(chosen: Int, crabs: Seq[Int]): Long = crabs.map(crab => Math.abs(chosen-crab)).map(distance => (distance*(distance+1))/2).sum

}
