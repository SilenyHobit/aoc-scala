package net.hobitin.aoc2021.day12

class Node(val name: String,
           private var linked: Seq[Node] = Seq.empty,
           private var visited: Int = 0) {

  private val isLowerCased = name == name.toLowerCase

  def link(node: Node): Node = {
    linked = linked.appended(node)

    this
  }

  def walk1(): Int = {
    walk(Seq.empty)((node, _) => node.isAvailable1)
  }

  def walk2(): Int = {
    walk(Seq.empty)(_.isAvailable2(_))
  }

  private def walk(path: Seq[Node])(availableCheck: (Node, Seq[Node]) => Boolean): Int = {
    if (name == "end") 1
    else {
      visited += 1
      val result = linked
        .filter(availableCheck(_, path))
        .map(_.walk(path.appended(this))(availableCheck))
        .sum
      visited -= 1

      result
    }
  }

  private def isAvailable1: Boolean = !(isLowerCased && visited > 0)

  private def isAvailable2(path: Seq[Node]): Boolean = {
    if (name == "start" && visited == 1) false
    else if (isLowerCased && visited > 1) false
    else if (visited == 1 && isLowerCased && path.exists(node => node.isLowerCased && node.visited > 1)) false
    else true
  }

}
