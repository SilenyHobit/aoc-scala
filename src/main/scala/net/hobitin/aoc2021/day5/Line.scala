package net.hobitin.aoc2021.day5

case class Line(a: Point, b: Point) {

  def straightPoints(): Seq[Point] = {
    if (a.x == b.x)
      (Math.min(a.y, b.y) to Math.max(a.y,b.y)).map(index => Point(a.x, index))
    else if (a.y == b.y)
      (Math.min(a.x, b.x) to Math.max(a.x,b.x)).map(index => Point(index, a.y))
    else
      Seq.empty
  }

  def diagonalPoints(): Seq[Point] = {
    if (a.x != b.x && a.y != b.y) {
      val xSign = Integer.signum(b.x-a.x)
      val ySign = Integer.signum(b.y-a.y)
      (a.y to b.y by ySign)
        .zip(a.x to b.x by xSign)
        .map(pair => Point(pair._2,pair._1))
    } else
      Seq.empty
  }

}
