package net.hobitin

import scala.io.Source
import scala.util.Using

trait TaskMixin {

  def input(): Seq[String] = Using(Source.fromURL(getClass.getResource("input"))){
    source => source.getLines()
      .toSeq
  }.get

  def printFirst(value: AnyVal): Unit = {
    println("First task:")
    println(value)
  }

  def printSecond(value: AnyVal): Unit = {
    println("Second task:")
    println(value)
  }
}
