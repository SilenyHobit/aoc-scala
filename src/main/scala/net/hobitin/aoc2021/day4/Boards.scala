package net.hobitin.aoc2021.day4

class Boards private(private val boards: Seq[Board],
                     private val firstFinished: Option[Board] = None,
                     private val firstFinishedNumber: Int = -1,
                     private val lastFinished: Option[Board] = None,
                     private val lastFinishedNumber: Int = -1) {

  def mark(number: Int): Boards = {
    if (boards.nonEmpty) {
      val (finished, updated) = boards.map(_.mark(number))
        .partition(_.isFinished)

      if (finished.nonEmpty && firstFinished.isEmpty) new Boards(updated, Some(finished.head), number, Some(finished.head), number)
      else if (finished.nonEmpty) new Boards(updated, firstFinished, firstFinishedNumber, Some(finished.head), number)
      else new Boards(updated, firstFinished, firstFinishedNumber, lastFinished, lastFinishedNumber)

    } else this
  }

  def firstSum: Int = firstFinished.get.sum * firstFinishedNumber

  def lastSum: Int = lastFinished.get.sum * lastFinishedNumber

}

object Boards {
  def apply(boards: Seq[Board]): Boards = new Boards(boards)
}
