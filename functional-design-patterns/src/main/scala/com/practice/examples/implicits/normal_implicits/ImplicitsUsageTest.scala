package com.practice.examples.implicits.normal_implicits;

object ImplicitsUsageTest extends App {
  //1. The implicit is present in the package object
  val doubleAsInt : Int = 11.8

  println(s"The integer value for 11.8 is ${doubleAsInt}")


  import ImplicitUtils._
  //2. Using implicit present in a separate Unility object
  val list = List(1,2,3,4,5)
  println(s"The list as String is ${listToString(list)}")

  //3. Implicits in companion objects

  val a = 10
  val b = 20

  def add[T : Adder](a : T, b : T)(implicit ev : Adder[T]) = {
    ev.plus(a,b)
  }

  println(add(a,b))
}
