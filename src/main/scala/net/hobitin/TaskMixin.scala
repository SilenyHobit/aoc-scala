package net.hobitin

import scala.io.Source
import scala.util.Using

trait TaskMixin {

  def input(): Seq[String] = Using(Source.fromURL(getClass.getResource("input"))){
    source => source.getLines()
      .toSeq
  }.get

  def printFirst(value: Any): Unit = {
    println("First task:")
    println(value)
  }

  def printSecond(value: Any): Unit = {
    println("Second task:")
    println(value)
  }
}
