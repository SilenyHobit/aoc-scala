package net.hobitin.aoc2021.day9

class Vents private(private val grid: Array[Array[Int]]) {
  def riskLevel: Int = lowPoints
      .map(coords => grid(coords._1)(coords._2) + 1)
      .sum

  private def lowPoints: Seq[(Int, Int)] =
    (1 until grid.length - 1)
      .flatMap(i => (1 until grid(i).length - 1).map((i, _)))
      .filter(coords =>
        grid(coords._1 - 1)(coords._2) > grid(coords._1)(coords._2)
          && grid(coords._1 + 1)(coords._2) > grid(coords._1)(coords._2)
          && grid(coords._1)(coords._2 - 1) > grid(coords._1)(coords._2)
          && grid(coords._1)(coords._2 + 1) > grid(coords._1)(coords._2)
      )

  def basins: Int = {
    val basins = lowPoints
      .map(basinSize(_, Array.fill(grid.length, grid(0).length)(false)))
      .sortWith(_ > _)

    basins.head * basins(1) * basins(2)
  }

  private def basinSize(testedPoint: (Int, Int), visited: Array[Array[Boolean]]): Int =
    Seq((testedPoint._1 - 1, testedPoint._2),
      (testedPoint._1 + 1, testedPoint._2),
      (testedPoint._1, testedPoint._2 - 1),
      (testedPoint._1, testedPoint._2 + 1))
      .map(nextPoint => {
        if (testBasinPoint(nextPoint, (testedPoint._1, testedPoint._2), visited)) {
          visited(nextPoint._1)(nextPoint._2) = true
          basinSize(nextPoint, visited)
        } else 0
      })
      .sum + 1

  private def testBasinPoint(next: (Int, Int), origin: (Int, Int), visited: Array[Array[Boolean]]): Boolean =
    grid(next._1)(next._2) > grid(origin._1)(origin._2) &&
      grid(next._1)(next._2) < 9 &&
      !visited(next._1)(next._2)

}

object Vents {
  def apply(input: Seq[String]): Vents = {
    val length = input.head.length
    val grid = input
      .map(_.toCharArray.map(_ - '0').prepended(Int.MaxValue).appended(Int.MaxValue))
      .prepended(Array.fill(length + 2)(Int.MaxValue))
      .appended(Array.fill(length + 2)(Int.MaxValue))
      .toArray

    new Vents(grid)
  }
}
