package net.hobitin.aoc2021.day3

import scala.annotation.tailrec

class BinaryRates {

  private var commonality: Option[Array[Int]] = None

  def process(input: String): BinaryRates = {
    commonality match {
      case None => commonality = Some(input.toCharArray.zip(new Array[Int](input.length))
        .map(pair => if (pair._1 == '0') pair._2 - 1 else pair._2 + 1))
      case Some(array) => commonality = Some(input.toCharArray.zip(array)
        .map(pair => if (pair._1 == '0') pair._2 - 1 else pair._2 + 1))
    }

    this
  }

  def gammaRate: Int = {
    val gamma = commonality.get
      .map(number => if (number < 0) "0" else "1")
      .reduceLeft(_ + _)
    Integer.parseInt(gamma, 2)
  }

  def epsilonRate: Int = {
    val epsilon = commonality.get
      .map(number => if (number > 0) "0" else "1")
      .reduceLeft(_ + _)
    Integer.parseInt(epsilon, 2)
  }

  def findOxygenRating(input: Seq[String]): Int = findRating(input, 0, commonality.get, '0', '1')

  def findScrubberRating(input: Seq[String]): Int = findRating(input, 0, commonality.get, '1', '0')

  @tailrec
  private def findRating(input: Seq[String], index: Int, localCommonality: Array[Int], zeroMostCommon: Char, oneMostCommon: Char): Int = {
    if (input.size == 1) {
      Integer.parseInt(input.head, 2)
    } else {
      val (filtered, removed) = input.partition(value => {
        (localCommonality(index) < 0 && value.charAt(index) == zeroMostCommon) ||
          (localCommonality(index) >= 0 && value.charAt(index) == oneMostCommon)
      })

      val updatedCommonality = removed.foldLeft(localCommonality)((array, value) => {
        value.toCharArray.zip(array)
          .map(pair => if (pair._1 == '0') pair._2 + 1 else pair._2 - 1)
      })

      findRating(filtered, index + 1, updatedCommonality, zeroMostCommon, oneMostCommon)
    }
  }

}
