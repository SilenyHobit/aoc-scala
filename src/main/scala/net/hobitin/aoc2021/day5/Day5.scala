package net.hobitin.aoc2021.day5

import net.hobitin.Day

import java.util.regex.Pattern

object Day5 extends Day {
  private val pattern = Pattern.compile("(\\d+),(\\d+) -> (\\d+),(\\d+)")


  override def name: String = "Day 5: Hydrothermal Venture"

  override def firstTask: Any = {
    val array = Array.ofDim[Int](1000, 1000)

    lines.flatMap(line => line.straightPoints)
      .foreach(point => array(point.y)(point.x) += 1)

    array.map(inner => inner.count(_ > 1)).sum
  }

  override def secondTask: Any = {
    val array = Array.ofDim[Int](1000, 1000)

    lines.flatMap(line => line.straightPoints.appendedAll(line.diagonalPoints))
      .foreach(point => array(point.y)(point.x) += 1)

    array.map(inner => inner.count(_ > 1)).sum
  }

  def lines: Seq[Line] = {
    input.map{pattern.matcher}
      .filter(matcher => matcher.find())
      .map(matcher => Line(Point(matcher.group(1).toInt, matcher.group(2).toInt), Point(matcher.group(3).toInt, matcher.group(4).toInt)))
  }

}
