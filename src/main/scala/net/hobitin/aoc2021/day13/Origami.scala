package net.hobitin.aoc2021.day13

import net.hobitin.aoc2021.day13.Origami.commandPattern

import java.util.regex.Pattern

class Origami private(val grid: Array[Array[Boolean]]) {

  def fold(command: String): Origami = {
    val matcher = commandPattern.matcher(command)

    if (!matcher.matches()) throw new IllegalArgumentException("Unknown command" + command)

    (matcher.group(1), matcher.group(2)) match {
      case ("x", number) => foldLeft(number.toInt)
      case ("y", number) => foldUp(number.toInt)
    }
  }

  def dotsCount: Int =
    grid
      .map(_.count(identity))
      .sum

  private def foldLeft(index: Int): Origami = {
    val newGrid = grid
      .indices
      .map(y => (0 until index).map(grid(y)(_)).toArray)
      .toArray

    val doubleIndex = index*2
    grid
      .indices
      .foreach(y => (index + 1 until grid(0).length)
        .foreach(x => {
          val oldIndex = doubleIndex - x
          if (oldIndex >= 0) newGrid(y)(oldIndex) = grid(y)(x) || newGrid(y)(oldIndex)
          else grid(y)(x)
        })
      )

    new Origami(newGrid)
  }

  private def foldUp(index: Int): Origami = {
    var newGrid = grid
      .take(index)

    val doubleIndex = index*2
    newGrid = newGrid
      .zipWithIndex
      .map(tuple => {
        val oldIndex = doubleIndex-tuple._2
        if (oldIndex < grid.length) tuple._1
          .zip(grid(oldIndex))
          .map(values => values._1 || values._2)
        else tuple._1
      })

    new Origami(newGrid)
  }

}

object Origami {
  private val commandPattern = Pattern.compile("fold along ([xy])=(\\d+)")

  def apply(input: Seq[String]): Origami = {
    val points = input
      .map(_.split(","))
      .map(numbers => (numbers.head.toInt, numbers(1).toInt))

    val xDimension = points.map(_._1).max + 1
    val yDimension = points.map(_._2).max + 1

    val grid = Array.ofDim[Boolean](yDimension, xDimension)

    points.foreach(point => grid(point._2)(point._1) = true)

    new Origami(grid)
  }
}
