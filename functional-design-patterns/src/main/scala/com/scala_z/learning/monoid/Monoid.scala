package com.scala_z.learning.monoid

import scalaz.Scalaz._
import scalaz.Tags

object Monoid extends App {

  val numbers = List(1,2,3,4,5)
  println(s"The sum is: ${numbers.foldMap(identity)}") //The sum monoid takes precedence and is actually passed to foldMap implicitly.

  println(s"he product (6!) is :" +
    s"${numbers.foldMap(Tags.Multiplication.apply)}") //For the multiplication to work, we have to pass Tags.Multiplication.apply in order to make things work as expected.

  val strings = List("This is\n", "a list of\n", "strings!")

  println(strings.foldMap(identity)(stringConcatenation)) //We have explicitly passed our string concatenation monoid to make the last statement work correctly.

}
