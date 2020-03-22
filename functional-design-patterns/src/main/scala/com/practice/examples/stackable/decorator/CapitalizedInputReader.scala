package com.practice.examples.stackable.decorator.common

import java.io.{BufferedInputStream, InputStreamReader, BufferedReader, ByteArrayOutputStream}
import java.nio.charset.Charset
import java.util.Base64
import java.util.zip.GZIPOutputStream

import com.typesafe.scalalogging.LazyLogging

trait CapitalizedInputReader extends InputReader with LazyLogging {
  abstract override def readLines(): Stream[String] = super.readLines().map{
    line =>
    print(line.toUpperCase)
      line.toUpperCase}
}

trait CompressedInputReader extends InputReader with LazyLogging {
  abstract override def readLines(): Stream[String] = super.readLines().map {
    line =>
      val text = line.getBytes(Charset.forName("UTF-8"))
      logger.info(s"Length before compression ${text.length.toString}" )

      val output = new ByteArrayOutputStream()
      val compressor = new GZIPOutputStream(output)

      try {
        compressor.write(text, 0, text.length)
        val outputByteArray = output.toByteArray
        logger.info("Length after compression: {}", outputByteArray.length.toString)
        new String(outputByteArray, Charset.forName("UTF-8"))
      } finally {
        compressor.close()
        output.close()
      }
  }
}

trait Base64EncoderInputReaderTrait extends InputReader {
  abstract override def readLines(): Stream[String] = super.readLines().map {
     line => Base64.getEncoder.encodeToString(line.getBytes(Charset.forName("UTF-8")))
  }
}

trait CamelCaseLinesTrait extends InputReader {
  abstract override def readLines(): Stream[String] = super.readLines().map{
    line =>
      val word = line.split(" ")
      word.map{
         ws => ws.head.toUpper + ws.tail
      }.mkString(" ")
  }
}

object StackableTraitsExample {
  def main(args: Array[String]): Unit = {
    val stream = new BufferedReader(
      new InputStreamReader(
        new BufferedInputStream(this.getClass.getResourceAsStream("/Users/spachari/IdeaProjects/scala-design-patterns/structural-design-patterns/src/main/resources/com/ivan/nikolov/structural/decorator/data.txt"))
      )
    )

    try {
      val reader = new AdvancedInputReader(stream) with CapitalizedInputReader
      reader.readLines().foreach(println)
    }
  }
}
