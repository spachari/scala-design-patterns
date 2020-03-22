package com.ivan.nikolov.practice.monads

import java.io.{File, PrintWriter}


import scala.io.Source

package object IO {
  def readFile(path: String) =
    IOAction1(Source.fromFile(path).getLines())

  def writeFile(path: String, lines: Iterator[String]) =
    IOAction1({
      val file = new File(path)
      printToFile(file) { p => lines.foreach(p.println) }
    })

  private def printToFile(file: File)(writeOp: PrintWriter => Unit): Unit = {
    val writer = new PrintWriter(file)
    try {
      writeOp(writer)
    } finally {
      writer.close()
    }
  }
}
