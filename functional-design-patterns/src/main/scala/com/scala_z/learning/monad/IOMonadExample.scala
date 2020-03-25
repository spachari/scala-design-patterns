package com.scala_z.learning.monad

import com.scala_z.learning.monad.model.Person
import scalaz._
import effect._
import Scalaz._
import IO._

object IOMonadExample {

  def main(args: Array[String]): Unit = {
    args match {
      case Array(inputFile, isWriteToFile) =>
        val people = for {
          line <- readFile(inputFile)
          person <- Person.fromArray(line.split("\t"))
        } yield person
    }//.pure[IO]
  }
}
