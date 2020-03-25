package com.practice.examples.type_classes.using_implicit_object

object StatsExample {
  def main(args: Array[String]): Unit = {

    import Stats._

    val intVector = Vector(1, 3, 5, 6, 10, 12, 17, 18, 19, 30, 36, 40, 42, 66)
    val doubleVector = Vector(1.5, 3.6, 5.0, 6.6, 10.9, 12.1, 17.3, 18.4, 19.2, 30.9, 36.6, 40.2, 42.3, 66.0)

    println(s"Mean : ${mean(intVector)}")
    println(s"Median : ${median(intVector)}")
    println(s"Std dev : ${stddev(intVector)}")

    println(s"Mean : ${mean(doubleVector)}")
    println(s"Median : ${median(doubleVector)}")
    println(s"Std dev : ${stddev(doubleVector)}")

    //without implicits we get
    /*
    Error:(11, 28) could not find implicit value for evidence parameter of type com.practice.examples.type_classes.Number[Int]
    println(s"Mean : ${mean(intVector)}")
     */

  }

}
