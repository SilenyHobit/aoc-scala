package net.hobitin

import scala.io.Source
import scala.util.Using

trait Day {

  def name: String

  def firstTask: Any

  def secondTask: Any


  def input: Seq[String] = Using(Source.fromURL(getClass.getResource("input")))(_.getLines().toSeq).get

}
