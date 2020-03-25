package com.practice.examples.partial_functions


object PartiallyDefinedFunctions  {
  val squareRoot : PartialFunction[Int, Double] = {
    case x if (x > 0) => Math.sqrt(x)
  }

  val multiplyBy2 : PartialFunction[Double, Double] = {
    case x => x * 2
  }

  val squares : PartialFunction[Int, Double] = {
    case x => Math.pow(x, 2)
  }
}

object PartiallyDefinedFunctionsTest extends App {

  import PartiallyDefinedFunctions._

  val list = List(-1,-20, -15, 0, 1, 2, 3, 5, 10)

  val output = list.collect(squareRoot)

  println(s"Output of the list is ${output}")

  val booleanOutput = squareRoot.isDefinedAt(-10)

  println(s"isDefinedAt for list is ${list.map(x => squareRoot.isDefinedAt(x))}")

  val squareRootAndMultiplyBy2 = list.collect(squareRoot.andThen(multiplyBy2))

  println(s"Output of chaining the functions is ${squareRootAndMultiplyBy2}")

  val squareRootOrSquare = list.collect(squareRoot.orElse(squares))

  println(s"Output of chaining the functions is ${squareRootOrSquare}")
}