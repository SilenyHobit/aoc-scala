package net.hobitin.aoc2021.day5

import net.hobitin.TaskMixin

import java.util.regex.Pattern

object Main extends TaskMixin {
  private val pattern = Pattern.compile("(\\d+),(\\d+) -> (\\d+),(\\d+)")
  def main(args: Array[String]): Unit = {
    val array = Array.ofDim[Int](1000, 1000)
    val lines = input().map{pattern.matcher}
      .filter(matcher => matcher.find())
      .map(matcher => Line(Point(matcher.group(1).toInt, matcher.group(2).toInt), Point(matcher.group(3).toInt, matcher.group(4).toInt)))

    lines.flatMap(line => line.straightPoints())
      .foreach(point => array(point.y)(point.x) += 1)

    printFirst(array.map(inner => inner.count(_ > 1)).sum)

    lines.flatMap(line => line.diagonalPoints())
      .foreach(point => array(point.y)(point.x) += 1)

    printSecond(array.map(inner => inner.count(_ > 1)).sum)
  }
}
