package net.hobitin.aoc2021.day7

import net.hobitin.TaskMixin

object Main extends TaskMixin{

  val cache: Array[Long] = new Array[Long](2001)

  def main(args:Array[String]): Unit = {
    val crabs = input.head
      .split(",")
      .map(_.toInt)
      .sortWith(_<_)
      .toSeq

    printFirst(sumFuel(crabs(crabs.length/2), crabs))

    (1 to 2000).foreach(index => cache(index) = cache(index-1) + index)

    val min = (0 to 1200).map(sumFuel2(_, crabs)).min

    printSecond(min)
  }

  def sumFuel(chosen: Int, crabs: Seq[Int]): Int = crabs.map(crab => Math.abs(chosen-crab)).sum

  def sumFuel2(chosen: Int, crabs: Seq[Int]): Long = crabs.map(crab => Math.abs(chosen-crab)).map(cache(_)).sum

}
