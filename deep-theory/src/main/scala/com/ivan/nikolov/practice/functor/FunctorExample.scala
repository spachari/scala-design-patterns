package com.ivan.nikolov.practice.functor

object FunctorExample  {
  def main(args: Array[String]): Unit = {
    val numbers = List(1, 2, 3, 4, 5, 6)
    val mapping = Map(
      1 -> "one",
      2 -> "two",
      3 -> "three",
      4 -> "four",
      5 -> "five",
      6 -> "six"
    )

    println(s"The numbers doubled are : ${listFunctors.map(numbers)(x => x * 2)}")
    println(s"The numbers wiht Strings are : ${listFunctors.map(numbers)(x => (x, mapping(x)))}")
  }
}
