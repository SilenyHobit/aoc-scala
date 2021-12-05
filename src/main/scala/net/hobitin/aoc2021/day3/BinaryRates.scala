package net.hobitin.aoc2021.day3

import scala.annotation.tailrec

class BinaryRates {

  private var commonality: Option[Array[Int]] = None

  def process(input: String): BinaryRates = {
    commonality match {
      case None => commonality = Some(input.toCharArray.zip(new Array[Int](input.length))
          .map(pair => if(pair._1 == '0') pair._2-1 else pair._2+1))
      case Some(array) => commonality = Some(input.toCharArray.zip(array)
        .map(pair => if(pair._1 == '0') pair._2-1 else pair._2+1))
    }

    this
  }

  def gammaRate(): Int = {
    val gamma = commonality.get
      .map(number => if (number<0) "0" else "1")
      .reduce(_+_)
    Integer.parseInt(gamma, 2)
  }

  def epsilonRate(): Int = {
    val epsilon = commonality.get
      .map(number => if (number>0) "0" else "1")
      .reduce(_+_)
    Integer.parseInt(epsilon, 2)
  }

  def findOxygenRating(input: Seq[String]): Int = {
    findOxygenRating(input, 0, commonality.get)
  }

  @tailrec
  private def findOxygenRating(input: Seq[String], index: Int, localCommonality: Array[Int]): Int = {
    if (input.size == 1)
      return Integer.parseInt(input.head, 2)

    val (filtered, removed) = input.partition(value => {
      (localCommonality(index) < 0 && value.charAt(index) == '0') ||
        (localCommonality(index) >= 0 && value.charAt(index) == '1')
    })

    val updatedCommonality = removed.foldLeft(localCommonality)((array, value) => {
      value.toCharArray.zip(array)
        .map(pair => if(pair._1 == '0') pair._2+1 else pair._2-1)
    })

    findOxygenRating(filtered, index+1, updatedCommonality)
  }

  def findScrubberRating(input: Seq[String]): Int = {
    findScrubberRating(input, 0, commonality.get)
  }

  @tailrec
  private def findScrubberRating(input: Seq[String], index: Int, localCommonality: Array[Int]): Int = {
    if (input.size == 1)
      return Integer.parseInt(input.head, 2)

    val (filtered, removed) = input.partition(value => {
      (localCommonality(index) < 0 && value.charAt(index) == '1') ||
        (localCommonality(index) >= 0 && value.charAt(index) == '0')
    })

    val updatedCommonality = removed.foldLeft(localCommonality)((array, value) => {
      value.toCharArray.zip(array)
        .map(pair => if(pair._1 == '0') pair._2+1 else pair._2-1)
    })

    findScrubberRating(filtered, index+1, updatedCommonality)
  }

}
