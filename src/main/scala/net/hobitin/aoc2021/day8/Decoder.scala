package net.hobitin.aoc2021.day8

object Decoder {
  def decode(input: String): Int = {
    val inputArray = input.split("\\|")
    val digits = inputArray(0)
      .split(" ")
      .filter(_.nonEmpty)
      .map(digit => digit.toCharArray.sorted.foldLeft("")(_+_))

    val one = digits.filter(_.length == 2).head
    val four = digits.filter(_.length == 4).head
    val seven = digits.filter(_.length == 3).head
    val eight = digits.filter(_.length == 7).head

    val sixLineDigits = digits.filter(_.length == 6)

    val six = sixLineDigits
      .filter(digit => !one.toCharArray.forall(digit.contains(_)))
      .head

    val nine = sixLineDigits
      .filter(digit => four.toCharArray.forall(digit.contains(_)))
      .head

    val zero = sixLineDigits
      .filter(six != _)
      .filter(nine != _)
      .head

    val fiveLineDigits = digits.filter(_.length == 5)

    val three = fiveLineDigits
      .filter(digit => one.toCharArray.forall(digit.contains(_)))
      .head

    val lines = "abcdefg".toCharArray
    val sixMissingLine = lines.filter(!six.contains(_)).head

    val two = fiveLineDigits
      .filter(three != _)
      .filter(_.contains(sixMissingLine))
      .head

    val five = fiveLineDigits
      .filter(three != _)
      .filter(two != _)
      .head

    inputArray(1)
      .trim
      .split(" ")
      .map(digit => digit.toCharArray.sorted.foldLeft("")(_+_))
      .map {
        case x if zero.equals(x) => "0"
        case x if one.equals(x) => "1"
        case x if two.equals(x) => "2"
        case x if three.equals(x) => "3"
        case x if four.equals(x) => "4"
        case x if five.equals(x) => "5"
        case x if six.equals(x) => "6"
        case x if seven.equals(x) => "7"
        case x if eight.equals(x) => "8"
        case x if nine.equals(x) => "9"
      }
      .reduceLeft(_+_)
      .toInt
  }
}
