package com.practice.examples.type_classes.using_implicit_vals

object AddableTest extends App {
  import Addable._

  println(s"Sum of int 1 and 2 is ${sum(1,2)}")
  println(s"Concatenating the strings 'Srinivas' and 'Pachari' is ${sum("Srinivas", "Pachari")}")

}
