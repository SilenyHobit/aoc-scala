package net.hobitin.aoc2021.day16

import net.hobitin.Day

object Day16 extends Day {
  override def name: String = "Day 16: Packet Decoder"

  override def firstTask: Any = {
    tree.versionSum
  }

  override def secondTask: Any = {
    tree.calculate
  }

  private def tree: Node = {
    val binaryInput = input
      .head
      .toCharArray
      .map {
        case '0' => "0000"
        case '1' => "0001"
        case '2' => "0010"
        case '3' => "0011"
        case '4' => "0100"
        case '5' => "0101"
        case '6' => "0110"
        case '7' => "0111"
        case '8' => "1000"
        case '9' => "1001"
        case 'A' => "1010"
        case 'B' => "1011"
        case 'C' => "1100"
        case 'D' => "1101"
        case 'E' => "1110"
        case 'F' => "1111"
      }
      .reduce(_+_)

    Node.parse(binaryInput)
  }
}
