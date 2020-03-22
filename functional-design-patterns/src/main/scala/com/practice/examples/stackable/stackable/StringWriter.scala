package com.practice.examples.stackable.stackable

abstract class StringWriter {
  def write(data : String) : String
}

class BasicStringWriter extends StringWriter {
  override def write(data: String): String = s"Writing the following data ${data}"
}

trait CapitalizedStringWriter extends StringWriter {
  abstract override def write(data: String): String = {
    super.write(data.split("\\s+").map(_.capitalize).mkString(" "))
  }
}

trait UpperCasingStringWriter extends StringWriter {
  abstract override def write(data: String): String = {
    super.write(data.toUpperCase())
  }
}

trait LowerCasingStringWriter extends StringWriter {
  abstract override def write(data: String): String = {
    super.write(data.toLowerCase())
  }
}

object StringWriterTest {
  def main(args: Array[String]): Unit = {
    val writer1 = new BasicStringWriter
      with UpperCasingStringWriter
      with CapitalizedStringWriter
    val writer2 = new BasicStringWriter
      with CapitalizedStringWriter
      with LowerCasingStringWriter
    val writer3 = new BasicStringWriter
      with CapitalizedStringWriter
      with UpperCasingStringWriter
      with LowerCasingStringWriter
    val writer4 = new BasicStringWriter
      with CapitalizedStringWriter
      with LowerCasingStringWriter
      with UpperCasingStringWriter

    val string = "we like learning scala!"

    println(s"Writer 1: ${writer1.write(string)}")

    println(s"Writer 2: ${writer2.write(string)}")

    println(s"Writer 3: ${writer3.write(string)}")

    println(s"Writer 4: ${writer4.write(string)}")

  }
}