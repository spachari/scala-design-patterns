package com.practice.examples.implicits.normal_implicits;

object ImplicitUtils {

  def listToString(list : List[Int]) : String = list.map(x => x.toString).mkString("[",",","]")

}
