package net.hobitin.aoc2021.day14

import net.hobitin.Day

object Day14 extends Day {

  override def name: String = "Day 14: Extended Polymerization"

  override def firstTask: Any = {
    execute(10)
  }

  override def secondTask: Any = {
    execute(40)
  }

  private def execute(runs: Int): Long = {
    val polymer = input.head

    val rules = input
      .drop(2)
      .map(_.split(" -> "))
      .map(rule => (rule(0), (s"${rule(0)(0)}${rule(1)}", s"${rule(1)}${rule(0)(1)}")))
      .groupMapReduce(_._1)(_._2)((a, _) => a)

    val pairs =  polymer
      .sliding(2)
      .toSeq
      .groupMap(identity)(_ => 1L)
      .view
      .mapValues(_.sum)
      .toMap

    val counter = new Array[Long](26)
    polymer.foreach(value => counter(value-'A') += 1)

    (0 until runs)
      .foldLeft(pairs)((polymerPairs, _) => {
        polymerPairs
          .toSeq
          .flatMap(tuple => {
            val rule = rules(tuple._1)
            counter(rule._1(1)-'A') += tuple._2
            Seq((rule._1, tuple._2), (rule._2, tuple._2))
          })
          .groupMapReduce(_._1)(_._2)(_+_)
      })

    counter.max - counter.filter(_ != 0).min
  }

}
