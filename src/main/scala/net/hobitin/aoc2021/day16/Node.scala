package net.hobitin.aoc2021.day16

sealed abstract class Node(val version: Int, val typeId: Int) {
  def versionSum: Int
  def calculate: Long
}

object Node {
  def parse(binaryString: String): Node = {
    parseInternal(binaryString)._1
  }

  private def parseInternal(binaryString: String): (Node, Int) = {
    val version = Integer.parseInt(binaryString.substring(0, 3), 2)
    val typeId = Integer.parseInt(binaryString.substring(3, 6), 2)
    var bytesRead = 6

    typeId match {
      case 4 => {
        val literal = readValue(binaryString.substring(6))
        (Literal(version, java.lang.Long.parseLong(literal._1, 2)), literal._2 + bytesRead)
      }
      case _ => {
        if(binaryString(6) == '0') {
          val length = Integer.parseInt(binaryString.substring(7, 22), 2)
          bytesRead += 16
          val children = parseSubPacketsByLength(length, binaryString.substring(bytesRead))
          (Operation(version, typeId, children), bytesRead + length)
        } else {
          val remaining = Integer.parseInt(binaryString.substring(7, 18), 2)
          bytesRead += 12
          val children = parseSubPacketsByNumber(remaining, binaryString.substring(bytesRead))
          (Operation(version, typeId, children._1), children._2 + bytesRead)
        }
      }
    }
  }

  private def parseSubPacketsByLength(length: Int, binaryString: String): Seq[Node] = {
    val result = parseInternal(binaryString)
    if (result._2 == length) Seq(result._1)
    else parseSubPacketsByLength(length - result._2, binaryString.substring(result._2)).prepended(result._1)
  }

  private def parseSubPacketsByNumber(remaining: Int, binaryString: String): (Seq[Node], Int) = {
    val result = parseInternal(binaryString)
    if (remaining == 1) (Seq(result._1), result._2)
    else {
      val subPackets = parseSubPacketsByNumber(remaining - 1, binaryString.substring(result._2))
      (subPackets._1.prepended(result._1), subPackets._2 + result._2)
    }
  }

  private def readValue(binaryString: String): (String, Int) = {
    if (binaryString.startsWith("0")) (binaryString.substring(1, 5), 5)
    else {
      val result = readValue(binaryString.substring(5))
      (binaryString.substring(1, 5) + result._1, result._2 + 5)
    }
  }
}

case class Literal(override val version: Int, value: Long)
    extends Node(version, 4) {

  override def versionSum: Int = version

  override def calculate: Long = value

}

case class Operation(override val version: Int,
                     override val typeId: Int,
                     children: Seq[Node])
    extends Node(version, typeId) {

  override def versionSum: Int = children.map(_.versionSum).sum + version

  override def calculate: Long = {
    typeId match {
      case 0 => children.map(_.calculate).sum
      case 1 => children.map(_.calculate).product
      case 2 => children.map(_.calculate).min
      case 3 => children.map(_.calculate).max
      case 5 => if (children.head.calculate > children(1).calculate) 1L else 0L
      case 6 => if (children.head.calculate < children(1).calculate) 1L else 0L
      case 7 => if (children.head.calculate == children(1).calculate) 1L else 0L
    }
  }
}
