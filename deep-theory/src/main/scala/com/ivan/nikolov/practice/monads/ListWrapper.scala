package com.ivan.nikolov.practice.monads

class ListWrapper(list : List[Int]) {

  def unit(x : List[Int]) : ListWrapper = ListWrapper.apply(x)

  def map[B](f : Int => B) : List[B] = list.map(f)

  def flatMap[B](f : Int => List[B]) : List[B] = list.flatMap(f)

}

object ListWrapper {
  def apply(x : List[Int]) = new ListWrapper(x)
}

object ForComprehension { //for comprehension on a normal List
  def main(args: Array[String]): Unit = {
    val list1 = List(1,2,3,4,5)
    val list2 = List(6,7,8,9,10)

    val outputList = for {
      x <- list1
      y <- list2
    } yield x * y //yield block is important

    println(outputList)
  }
}

object ForComprehensionWihtObjects { //describing map and flatMap will be useful for putting it in a for comprehension
  def main(args: Array[String]): Unit = {
    val list1 = ListWrapper(List(1,2,3,4,5))
    val list2 = ListWrapper(List(6,7,8,9,10))


    val outputList = for {
      x <- list1
      y <- list2
    } yield x * y



    /*
    If we rename any of them, we would get a compilation error—we could still manage to write the same code but it will
    not be able to use syntactic sugar in Scala. Another point here is that the for comprehension would work correctly in
    the case where both the methods actually follow the rules for map and flatMap.
     */

    println(outputList)
  }
}

