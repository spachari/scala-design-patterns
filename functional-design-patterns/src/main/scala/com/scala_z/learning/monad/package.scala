package com.scala_z.learning

import java.io.{File, PrintWriter}

import scala.io.Source

package object monad {

  //let's look at the IO monad in Scalaz that can be used to perform I/O in a monadic way.

  def readFile(path : String) = {
    println(s"Reading file ${path}")
    Source.fromFile(path).getLines()
  }

  def writeFile(path : String, lines : Iterator[String]) = {
    println(s"Writing file ${path}")
    val file = new File(path)
    printToFile(file)(p => lines.foreach(p.println))
  }

  private def printToFile(file : File)(writeOp : PrintWriter => Unit) = {
    val writer = new PrintWriter(file)
    try {
      writeOp(writer)
    } finally {
      writer.close()
    }
  }

}
