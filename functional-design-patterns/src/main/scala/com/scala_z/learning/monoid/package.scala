package com.scala_z.learning

import scalaz.Monoid

package object monoid {

  val stringConcatenation = new Monoid[String] {
    override def zero: String = " "

    override def append(f1: String, f2: => String): String =  f1 + f2
  }

}
