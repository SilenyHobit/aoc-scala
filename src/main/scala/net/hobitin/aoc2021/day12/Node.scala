package net.hobitin.aoc2021.day12

class Node(val name: String,
           private var linked: Seq[Node] = Seq.empty,
           private var visited: Int = 0) {

  private val isLowerCased = name == name.toLowerCase

  def link(node: Node): Node = {
    linked = linked.appended(node)

    this
  }

  def walk1(): Seq[Seq[Node]] = {
    walk(Seq.empty)((node, _) => node.isAvailable1)
  }

  def walk2(): Seq[Seq[Node]] = {
    walk(Seq.empty)((node, path) => node.isAvailable2(path))
  }

  private def walk(path: Seq[Node])(availableCheck: (Node, Seq[Node]) => Boolean): Seq[Seq[Node]] = {
    if (name == "end") Seq(path.appended(this))
    else {
      visited += 1
      val result = linked
        .filter(availableCheck(_, path))
        .map(_.walk(path.appended(this))(availableCheck))
        .filter(_.nonEmpty)
      visited -= 1

      if (result.isEmpty) Seq.empty else result.reduce(_.appendedAll(_))
    }
  }

  private def isAvailable1: Boolean = !(isLowerCased && visited > 0)

  private def isAvailable2(path: Seq[Node]): Boolean = {
    if (name == "start" && visited == 1) false
    else if (isLowerCased && visited > 1) false
    else if (visited == 1 && name.toLowerCase == name && path.count(node => node.isLowerCased && node.visited > 1) > 0) false
    else true
  }

}
