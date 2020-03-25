package com.practice.examples.implicits.normal_implicits

trait Adder[T] {
  def plus(x : T, y : T) : T
}

object Adder {
  implicit val intPlus = new Adder[Int] {
    override def plus(x: Int, y: Int): Int = x + y
  }
}