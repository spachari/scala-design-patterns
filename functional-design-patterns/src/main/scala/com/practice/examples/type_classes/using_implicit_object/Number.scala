package com.practice.examples.type_classes.using_implicit_object

package com.practice.examples.type_classes

trait Number[T] {
  def plus(x : T, y : T) : T
  def minus(x : T, y : T) : T
  def divide(x : T, y : Int) : T
  def multiply(x : T, y : T) : T
  def sqrt(x : T) : T
}

import Math._

object Number {
  implicit object DoubleNumber extends Number[Double] {
    override def plus(x: Double, y: Double): Double = x + y

    override def minus(x: Double, y: Double): Double = x - y

    override def divide(x: Double, y: Int): Double = x / y

    override def multiply(x: Double, y: Double): Double = x * y

    override def sqrt(x: Double): Double = x * x
  }

  implicit object IntNumber extends Number[Int] {
    override def plus(x: Int, y: Int): Int = x + y

    override def minus(x: Int, y: Int): Int = x - y

    override def divide(x: Int, y: Int): Int = x / y

    override def multiply(x: Int, y: Int): Int = x * y

    override def sqrt(x: Int): Int = x * x
  }
}

/*
Defining your default type class members in the companion object

The companion object of the implicit type class parameter is the last place the compiler looks for implicit values.
This means that nothing extra has to be done and users can easily override our implementations.
 */