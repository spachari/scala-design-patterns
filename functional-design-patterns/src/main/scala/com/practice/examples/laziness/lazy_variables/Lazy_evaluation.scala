package com.practice.examples.laziness.lazy_variables



//1. lazy initialization in Scala defers it until the variable is used for the first time.
//2. it is calculated only once, so are all vals
//3. Note : Only def is calculated every time it is called

object CircleUtils {
  val basicPi = 3.14
  val map = Map("pi.high" -> 3.1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679)

  lazy val precisePi = {
    println("Reading properties for the precise Pi")
    map("pi.high").toDouble
  }

  def area(radius : Double, isPrecise : Boolean = false) = {
    val pi = if (isPrecise) precisePi else basicPi
    pi * math.pow(radius, 2)
  }
}

object Lazy_evaluation extends App {

  println(s"The basic area for a circle with radius 2.5 is ${CircleUtils.area(2.5)}")
  println(s"The precise area for a circle with radius 2.5 is ${CircleUtils.area(2.5, true)}")
  println(s"The basic area for a circle with radius 2.5 is ${CircleUtils.area(2.5)}")
  println(s"The precise area for a circle with radius 2.5 is ${CircleUtils.area(2.5, true)}")


}
