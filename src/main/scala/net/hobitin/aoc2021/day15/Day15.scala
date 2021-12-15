package net.hobitin.aoc2021.day15

import net.hobitin.Day

import scala.annotation.tailrec
import scala.collection.mutable

object Day15 extends Day {

  override def name: String = "Day 15: Chiton"

  override def firstTask: Any = {
    val array = input
      .map(line => line.toCharArray.map(_ - '0'))
      .toArray

    walk(Step(0, (0, 0)), array, new mutable.PriorityQueue[Step](), Array.ofDim[Boolean](array.length, array(0).length))
      .cost
  }

  override def secondTask: Any = {
    val segment = input
      .map(line => line.toCharArray.map(_ - '0'))
      .toArray

    val rowSegments = segment
      .map(row =>
        (0 until 5)
          .map(increment => row.map(_ + increment).map(value => if (value > 9) value - 9 else value))
          .reduceLeft(_.appendedAll(_))
      )

    val extended = (0 until 5)
      .flatMap(increment =>
        rowSegments
          .map(row => row.map(_ + increment).map(value => if (value > 9) value - 9 else value))
      )
      .toArray

    walk(Step(0, (0, 0)), extended, new mutable.PriorityQueue[Step](), Array.ofDim[Boolean](extended.length, extended(0).length))
      .cost
  }

  @tailrec
  private def walk(step: Step, array: Array[Array[Int]], queue: mutable.PriorityQueue[Step], visited: Array[Array[Boolean]]): Step = {
    if (step.point._1 == array.length - 1 && step.point._2 == array(0).length - 1) step
    else {
      Seq(
        (step.point._1 - 1, step.point._2),
        (step.point._1 + 1, step.point._2),
        (step.point._1, step.point._2 - 1),
        (step.point._1, step.point._2 + 1)
      )
        .filter(point => point._1 >= 0 && point._1 < array.length && point._2 >= 0 && point._2 < array(0).length && !visited(point._1)(point._2))
        .map(point => Step(step.cost + array(point._1)(point._2), point))
        .foreach(nextStep => {
          visited(nextStep.point._1)(nextStep.point._2) = true
          queue.enqueue(nextStep)
        })

      walk(queue.dequeue(), array, queue, visited)
    }
  }

}
