package net.hobitin.aoc2021.day11

import scala.annotation.tailrec

class Octopuses private(private val grid: Array[Array[Int]], val flashes: Int = 0, private val steps: Int = 1, val allFlashedOn: Option[Int] = None) {

  def step: Octopuses = update(grid.map(_.map(_ + 1)), Array.fill(grid.length, grid(0).length)(false))

  @tailrec
  private def update(input: Array[Array[Int]], flashed: Array[Array[Boolean]]): Octopuses = {
    val flashedNow = flashed
      .zipWithIndex
      .map(rowTuple => {
        rowTuple._1
          .zipWithIndex
          .map(columnTuple => !columnTuple._1 && input(rowTuple._2)(columnTuple._2) > 9)
      })

    val updatedFlashed = flashed
      .zip(flashedNow)
      .map(tuple => tuple._1.zip(tuple._2).map(values => values._1 || values._2))

    val updatedInput = input
      .zipWithIndex
      .map(rowTuple => {
        rowTuple._1
          .zipWithIndex
          .map(columnTuple => columnTuple._1 + countFlashes((rowTuple._2, columnTuple._2), flashedNow))
      })

    if (equals(flashed, updatedFlashed)) reset(updatedInput, updatedFlashed)
    else update(updatedInput, updatedFlashed)
  }

  private def equals(first: Array[Array[Boolean]], second: Array[Array[Boolean]]): Boolean =
    first.zip(second).map(tuple => tuple._1.zip(tuple._2).map(inner => inner._1 == inner._2).reduce(_&&_)).reduce(_&&_)

  private def reset(array: Array[Array[Int]], flashed: Array[Array[Boolean]]): Octopuses = {
    val newAllFlashedStep: Option[Int] = if (flashed.forall(_.forall(identity))) Some(steps) else None

    val newGrid = array
      .zipWithIndex
      .map(rowTuple => {
        rowTuple._1
          .zipWithIndex
          .map(columnTuple => {
            if (flashed(rowTuple._2)(columnTuple._2)) 0
            else columnTuple._1
          })
      })
    val newFlashes = flashed.map(_.count(identity)).sum + flashes

    new Octopuses(newGrid, newFlashes, steps+1, newAllFlashedStep)
  }

  private def countFlashes(coords: (Int, Int), flashed: Array[Array[Boolean]]): Int = {
    val xDecr = coords._1-1
    val yDecr = coords._2-1
    val xIncr = coords._1+1
    val yIncr = coords._2+1

    var value = 0
    if (xDecr >= 0 && flashed(xDecr)(coords._2)) value += 1
    if (xDecr >= 0 && yDecr >= 0 && flashed(xDecr)(yDecr)) value += 1
    if (xDecr >= 0 && yIncr < 10 && flashed(xDecr)(yIncr)) value += 1
    if (yDecr >= 0 && flashed(coords._1)(yDecr)) value += 1
    if (yIncr < 10 && flashed(coords._1)(yIncr)) value += 1
    if (xIncr < 10 && yDecr >= 0 && flashed(xIncr)(yDecr)) value += 1
    if (xIncr < 10 && flashed(xIncr)(coords._2)) value += 1
    if (xIncr < 10 && yIncr < 10 && flashed(xIncr)(yIncr)) value += 1

    value
  }
}

object Octopuses {
  def apply(input: Seq[String]): Octopuses =
    new Octopuses(
      input
        .map(_.toCharArray.map(_ - '0'))
        .toArray
    )
}
