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
        val people = {
          for {
            line <- readFile(inputFile)
            person <- Person.fromArray(line.split("\t"))
          } yield person
          }.pure[IO]

        println("Still I have not done any IO")
        println("About to do some")

        if (isWriteToFile.toBoolean) {
          val writePeople = for {
            _ <- putStrLn("Read people successfully. Where to write them down")
            outputFile <- readLn
            p <- people
            _ <- writeFile(outputFile, p.map(_.toString)).pure[IO]
           } yield ()

          println("Writing to ile using toString")
          writePeople.unsafePerformIO
        } else {
          println(s"Just got the following people ${people.unsafePerformIO.toList}")
        }

      case _ => println("Please provide input file and true/false whether to write the file")
        System.exit(-1)
    }
  }
}
