package net.hobitin.aoc2021.day12

import net.hobitin.Day

object Day12 extends Day {
  override def name: String = "Day 12: Passage Pathing"

  override def firstTask: Any = {
    buildNodes
      .filter(_.name == "start")
      .head
      .walk1()
      .size
  }

  override def secondTask: Any = {
    buildNodes
      .filter(_.name == "start")
      .head
      .walk2()
      .size
  }

  private def buildNodes: Seq[Node] = {
    val nodeMap = input
      .map(_.split("-"))
      .flatMap(nodes => {
        Seq((nodes.head, nodes.tail.head), (nodes.tail.head, nodes.head))
      })
      .groupMap(_._1)(_._2)

    val nodes = nodeMap
      .keys
      .map(new Node(_))
      .toSeq

    nodes
      .map(node => {
        nodeMap.get(node.name)
          .map(links =>
            links.foldLeft(node)((node, link) =>
              node.link(nodes.filter(_.name == link).head)
            )
          )
          .getOrElse(node)
      })
  }

}
