package com.ivan.nikolov.structural.decorator

import java.io.{BufferedInputStream, BufferedReader, ByteArrayOutputStream, InputStreamReader}
import java.nio.charset.Charset
import java.util.Base64
import java.util.zip.GZIPOutputStream

import com.ivan.nikolov.structural.decorator.common.{AdvancedInputReader, InputReader}
import com.typesafe.scalalogging.LazyLogging

/*
Abstract override allows us to call super for a method in a trait that is declared abstract. This is permissible for traits as long as
the trait is mixed in after another trait or a class that implements the preceding method. The abstract override tells the compiler that we
are doing this on purpose and it will not fail our compilation—it will check later, when we use the trait, whether the requirements for using
it are satisfied.
 */

trait CapitalizedInputReaderTrait extends InputReader {
  abstract override def readLines(): Stream[String] = super.readLines().map(_.toUpperCase)
}

trait CompressingInputReaderTrait extends InputReader with LazyLogging {
  abstract override def readLines(): Stream[String] = super.readLines().map {
    case line =>
      val text = line.getBytes(Charset.forName("UTF-8"))
      logger.info("Length before compression: {}", text.length.toString)
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
    case line => Base64.getEncoder.encodeToString(line.getBytes(Charset.forName("UTF-8")))
  }
}

trait CamelCaseLinesTrait extends InputReader {
  abstract override def readLines(): Stream[String] = super.readLines().map{
    line =>
      val word = line.split(" ")
      word.map{
        ws => ws.head.toUpper + ws.tail.toLowerCase
      }.mkString(" ")
  }
}


object StackableTraitsExample {
  def main(args: Array[String]): Unit = {
    val stream = new BufferedReader(
      new InputStreamReader(
        new BufferedInputStream(this.getClass.getResourceAsStream("data.txt"))
      )
    )
    try {
      val reader = new AdvancedInputReader(stream) with CapitalizedInputReaderTrait
      reader.readLines().foreach(println)
    } finally {
      stream.close()
    }
  }
}

object StackableTraitsBigExample {
  def main(args: Array[String]): Unit = {
    val stream = new BufferedReader(
      new InputStreamReader(
        new BufferedInputStream(this.getClass.getResourceAsStream("data.txt"))
      )
    )
    try {
      //This capitalizes, Base64 encodes, compresses and camel cases. So the order of implementation is from left to right
      val reader = new AdvancedInputReader(stream)  with CapitalizedInputReaderTrait with Base64EncoderInputReaderTrait //with CompressingInputReaderTrait
        with CamelCaseLinesTrait

      reader.readLines().foreach(println)
    } finally {
      stream.close()
    }
  }
}

/*
The fact that in our current example the modifications are applied from left to right is deceiving.
The reason this happens is because we push calls on the stack until we reach the basic implementation of readLines
and then apply modifications in a reverse order.
 */