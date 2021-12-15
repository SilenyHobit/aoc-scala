package net.hobitin.aoc2021.day15

case class Step(cost: Int, point: (Int, Int)) extends Comparable[Step] {
  override def compareTo(o: Step): Int = o.cost.compareTo(cost)
}
