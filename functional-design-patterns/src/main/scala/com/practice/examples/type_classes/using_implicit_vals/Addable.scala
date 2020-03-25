package com.practice.examples.type_classes.using_implicit_vals

trait Addable[T] {
  def add(a : T, b : T) : T
}

object Addable {

  def sum[T : Addable](x : T, y : T)(implicit ev: Addable[T]) = {
    ev.add(x,y)
  }

  implicit val intAdd = new Addable[Int] {
    override def add(a: Int, b: Int): Int = a + b
  }

  implicit val stringAdd = new Addable[String] {
    override def add(a: String, b: String): String = s"${a} is concatenated to ${b}"
  }
}




