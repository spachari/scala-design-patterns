package com.practice.examples.partial_functions

object PartiallyAppliedFunctions {

  val greaterOrEqual : (Int, Int) => Boolean = (a : Int, b : Int) => a >= b

  val lessThanOrEqual : (Int, Int) => Boolean = (a : Int, b : Int) => a <= b

  def greaterThanOrEqualCurried(b : Int)(a : Int) = a >= b

  def lessThanOrEqualCurried(b : Int)(a : Int) = a <= b

  val greaterThanOrEqualCurriedVal : Int => Int => Boolean = b => a => a >= b

  val lessThanOrEqualCurriendVal : Int => Int => Boolean = b => a => a <= b

}

//here is how to use them

object PartiallyAppliedFunctionTest {

  import PartiallyAppliedFunctions._

  val MAX = 20
  val MIN = 5

  def main(args: Array[String]): Unit = {
    val numbers = List(1, 5, 6, 11, 18, 19, 20, 21, 25, 30)

    //Partially applied
    val ge = greaterOrEqual(_ : Int, MIN)
    val le = lessThanOrEqual(_ : Int, MAX)

    //Curried
    val geCurried = greaterThanOrEqualCurried(MIN) _
    val leCurried = lessThanOrEqualCurried(MAX) _

    //Crurried Val
    val geCurriedVal = greaterThanOrEqualCurriedVal(MAX)
    val leCurriedVal = greaterThanOrEqualCurriedVal(MIN)

    //The reason is that curried functions are simply a chain of single parameter functions and the parameters are applied in the order we see them.
    //The line greaterOrEqualCurried(MIN) _ partially applies the function and returns a curried function that we can use similarly to the normal one.

    println(s"Filtered list: ${numbers.filter(i => ge(i) && le(i))}")
    println(s"Filtered list: ${numbers.filter(i => geCurried(i) && leCurried(i))}")


  }
}
