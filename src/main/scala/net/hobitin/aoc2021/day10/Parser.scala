package net.hobitin.aoc2021.day10

class Parser {

  var expected: List[Char] = List()
  var corrupted: Option[Char] = None

  def parse(input: String): Unit = {
    input.toCharArray.foreach {
      case '(' => expected = expected.prepended(')')
      case '{' => expected = expected.prepended('}')
      case '[' => expected = expected.prepended(']')
      case '<' => expected = expected.prepended('>')
      case x => {
        if (!expected.isEmpty && corrupted.isEmpty && x != expected.head) {
          corrupted = Some(x)
        } else if (x == expected.head){
          expected = expected.tail
        }
      }
    }
  }

  def corruptScore: Int = {
    corrupted match {
      case None => 0
      case Some(')') => 3
      case Some(']') => 57
      case Some('}') => 1197
      case Some('>') => 25137
    }
  }

  def isCorrupted: Boolean = corrupted.isDefined

  def autocompleteScore: Long = {
    var score = 0L
    expected.foreach(c => {
      score *= 5
      score += (c match {
        case ')' => 1
        case ']' => 2
        case '}' => 3
        case '>' => 4
      })
    })

    score
  }

}

object Parser {
  def apply(input: String): Parser = {
    val parser = new Parser
    parser.parse(input)

    parser
  }
}
