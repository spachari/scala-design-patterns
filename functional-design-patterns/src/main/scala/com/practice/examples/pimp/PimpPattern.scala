package com.practice.examples.pimp

import com.practice.examples.pimp.model.Person

/*
We basically added an extension method to the standard string that checks whether the entire string is in uppercase or not.
The only thing we need to do is make sure that the implicit class is available in the scope where we want to use the methods defined by it.
 */
object PimpPattern extends App {

  import pimp._

  println(s"Is Test all upper case?")
  val s = "Test"
  println(s.isAllUpperClass())
  val s1 = "TEST"
  println(s1.isAllUpperClass())

  val list = List(
    Person("Srinivas", 39),
    Person("Shankar", 40)
  )

  list.saveToDatabase()

}