package com.practice.examples.implicits

package object normal_implicits {
  implicit def doubleToInt(d : Double) : Int = Math.round(d).toInt
}
