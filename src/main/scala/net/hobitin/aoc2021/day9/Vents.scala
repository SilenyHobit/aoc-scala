package net.hobitin.aoc2021.day9

class Vents private (private val grid: Array[Array[Int]]) {
  def riskLevel: Int = {
    lowPoints
      .map(coords => grid(coords._1)(coords._2)+1)
      .sum
  }

  private def lowPoints: Seq[(Int, Int)] = {
    (1 until grid.length-1)
      .flatMap(i => (1 until grid(i).length-1).map((i,_)))
      .filter(coords =>
        grid(coords._1-1)(coords._2) > grid(coords._1)(coords._2) &&
          grid(coords._1+1)(coords._2) > grid(coords._1)(coords._2) &&
          grid(coords._1)(coords._2-1) > grid(coords._1)(coords._2) &&
          grid(coords._1)(coords._2+1) > grid(coords._1)(coords._2)
      )
  }

  def basins: Int = {
    val basins = lowPoints
      .map(basinSize(_, Array.fill(grid.length, grid(0).length)(false)))
      .sortWith(_>_)

    basins.head*basins(1)*basins(2)
  }

  private def basinSize(testedPoint: (Int, Int), visited: Array[Array[Boolean]]): Int = {
    var currentSize = 1
    if (grid(testedPoint._1-1)(testedPoint._2) > grid(testedPoint._1)(testedPoint._2) && grid(testedPoint._1-1)(testedPoint._2) < 9 && !visited(testedPoint._1-1)(testedPoint._2)) {
      visited(testedPoint._1-1)(testedPoint._2) = true
      currentSize += basinSize((testedPoint._1-1, testedPoint._2), visited)
    }

    if (grid(testedPoint._1+1)(testedPoint._2) > grid(testedPoint._1)(testedPoint._2) && grid(testedPoint._1+1)(testedPoint._2) < 9 && !visited(testedPoint._1+1)(testedPoint._2)) {
      visited(testedPoint._1+1)(testedPoint._2) = true
      currentSize += basinSize((testedPoint._1+1, testedPoint._2), visited)
    }

    if (grid(testedPoint._1)(testedPoint._2-1) > grid(testedPoint._1)(testedPoint._2) && grid(testedPoint._1)(testedPoint._2-1) < 9 && !visited(testedPoint._1)(testedPoint._2-1)) {
      visited(testedPoint._1)(testedPoint._2-1) = true
      currentSize += basinSize((testedPoint._1, testedPoint._2-1), visited)
    }

    if (grid(testedPoint._1)(testedPoint._2+1) > grid(testedPoint._1)(testedPoint._2) && grid(testedPoint._1)(testedPoint._2+1) < 9 && !visited(testedPoint._1)(testedPoint._2+1)) {
      visited(testedPoint._1)(testedPoint._2+1) = true
      currentSize += basinSize((testedPoint._1, testedPoint._2+1), visited)
    }

    currentSize
  }
}

object Vents {
  def apply(input: Seq[String]): Vents = {
    val length = input.head.length
    val grid = input
      .map(_.toCharArray.map(_ - '0').prepended(Int.MaxValue).appended(Int.MaxValue))
      .prepended(Array.fill(length+2)(Int.MaxValue))
      .appended(Array.fill(length+2)(Int.MaxValue))
      .toArray

    new Vents(grid)
  }
}
