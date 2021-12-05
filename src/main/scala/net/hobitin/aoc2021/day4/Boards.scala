package net.hobitin.aoc2021.day4

class Boards(private var boards: Seq[Board]) {
  private var firstFinished: Option[Board] = None
  private var firstFinishedNumber = -1
  private var lastFinished: Option[Board] = None
  private var lastFinishedNumber = -1

  def mark(number: Int): Boolean = {
    if (boards.isEmpty)
      return true

    val (finished, updated) = boards.map(_.mark(number))
      .partition(_.isFinished)


    if (finished.nonEmpty) {
      if (firstFinished.isEmpty) {
        firstFinished = Some(finished.head)
        firstFinishedNumber = number
      }

      lastFinished = Some(finished.head)
      lastFinishedNumber = number
    }

    boards = updated
    boards.isEmpty
  }

  def firstSum(): Int = {
    firstFinished.get.sum() * firstFinishedNumber
  }

  def lastSum(): Int = {
    lastFinished.get.sum() * lastFinishedNumber
  }

}
