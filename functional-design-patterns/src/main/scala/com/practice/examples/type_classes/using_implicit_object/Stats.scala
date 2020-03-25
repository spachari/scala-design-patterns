package com.practice.examples.type_classes.using_implicit_object

import com.practice.examples.type_classes.Number

object Stats {
  /*
  def mean[T : Number](xs : Vector[T])(implicit ev : Number[T]) = {
    ev.divide(xs.reduce(ev.plus(_,_)), xs.size)
  }
   */
  def mean[T : Number](xs : Vector[T]) = {
    val ev = implicitly[Number[T]]
    val sum = xs.reduce((tot, item) => ev.plus(tot , item))
    val count = xs.size
    ev.divide(sum, count)
  }

  // assumes the vector is sorted
  def median[T](xs : Vector[T])  = {
    xs(xs.size / 2)
  }

  def variance[T : Number](xs : Vector[T]) = {
    val ev = implicitly[Number[T]]
    val simpleMean = mean(xs)
    val sqDiff = xs.map {
      case diff => ev.minus(diff, simpleMean)
        ev.multiply(diff, diff)
    }
    mean(sqDiff)
  }

  def stddev[T : Number](xs : Vector[T]): T = {
    implicitly[Number[T]].sqrt(variance(xs))
  }
}
