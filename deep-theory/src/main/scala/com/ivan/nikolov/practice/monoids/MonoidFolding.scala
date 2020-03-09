package com.ivan.nikolov.practice.monoids



//Take a look at foldable computing
//def foldLeft[B](i : B)(f : (A,B) => B) //Note: A will be from the trait

//def foldRight[B](i : B)(f : (A,B) => B)

//If they are of the same type, sue this

//def foldLeft(i : A)(f : (A,A) => A)

object MonoidFolding {
  def main(args: Array[String]): Unit = {
    val strings = List("This is a\n", "list of\n", "strings")
    val numbers = List(1,2,3,4,5)

    println(s"Left folded = ${strings.foldLeft(stringConcatenation.id)((x : String, y : String) => stringConcatenation.op(x, y))}")
    println(s"Right folded = ${numbers.foldLeft(intAddition.id)((x : Int, y : Int) => intAddition.op(x,y))}")
    println(s"Left fold of multiplication = ${numbers.foldLeft(intMultiplication.id)((x : Int, y : Int) => intMultiplication.op(x,y))}")
  }
}


object MonoidFoldingGeneric {
  def main(args: Array[String]): Unit = {

    val strings = List("This is a\n", "list of\n", "strings")
    val numbers = List(1,2,3,4,5)

    println(s"Left folded : \n ${MonoidOperations.fold(strings, stringConcatenation)}")
    println(s"Left folded : \n ${MonoidOperations.fold(numbers, intAddition)}")
    println(s"Left folded : \n ${MonoidOperations.fold(numbers, intMultiplication)}")
  }
}

object BalancedFold {
  def main(args: Array[String]): Unit = {
    val list = Array(1,2,3,4)

    println(s"Left folded balanced way : ${MonoidOperations.balancedFold(list, intMultiplication)(identity)}")
  }
}

object ComposedMonoid {
  def main(args: Array[String]): Unit = {
    val array = Array(1,2,3,4,5,6)

    val sumAndProduct = compose(intAddition, intMultiplication)

    println(s"Left fold : ${MonoidOperations.balancedFold(array, sumAndProduct)(i => (i,i))}")
  }
}

object MonoidFeatureCounting {
  def main(args: Array[String]): Unit = {
    val features = Array("hello", "features", "for", "ml", "hello", "for", "features")

    val counterMonoid : Monoid[Map[String, Int]] = mapMerge(intAddition)

    println(s"The features are ${MonoidOperations.balancedFold(features, counterMonoid)(i => Map(i -> 1))}")
  }
}