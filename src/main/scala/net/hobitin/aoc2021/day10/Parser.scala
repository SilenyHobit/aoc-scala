package net.hobitin.aoc2021.day10

class Parser(private val input: String) {

  private val (expected: List[Char], corrupted: Option[Char]) = input
    .toCharArray
    .foldLeft((List[Char](), None.asInstanceOf[Option[Char]]))((tuple, character) => {
      character match {
        case '(' => (tuple._1.prepended(')'), tuple._2)
        case '{' => (tuple._1.prepended('}'), tuple._2)
        case '[' => (tuple._1.prepended(']'), tuple._2)
        case '<' => (tuple._1.prepended('>'), tuple._2)
        case x =>
          if (tuple._1.nonEmpty && tuple._2.isEmpty && x != tuple._1.head) (tuple._1, Some(x))
          else if (x == tuple._1.head) (tuple._1.tail, tuple._2)
          else tuple
      }
    })

  def corruptScore: Int = corrupted match {
    case None => 0
    case Some(')') => 3
    case Some(']') => 57
    case Some('}') => 1197
    case Some('>') => 25137
  }

  def isCorrupted: Boolean = corrupted.isDefined

  def autocompleteScore: Long =
    expected.foldLeft(0L)(
      (score, character) => {
        (score * 5) + (
          character match {
            case ')' => 1
            case ']' => 2
            case '}' => 3
            case '>' => 4
          })
      }
    )
}
