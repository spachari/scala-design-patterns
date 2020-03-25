package com.practice.examples.laziness.lazy_variables

case class Person (name : String, age : Int)

object Person {
  def getPersons : List[Person] = {
    println("Getting value from database ... ")
    Thread.sleep(3000)
    List(
      Person("Srinivas", 39),
      Person("Shankar", 40),
      Person("Sachin", 30)
    )
  }

  def printPeopleBad(people : => List[Person]) = { //this is nothing but a syntax of a type that takes nothing and gives a List[Person]
    println(s"Print first time ${people}")
    println(s"Print second time ${people}")
  }

  def printPeopleGood(people : => List[Person]) = {
    lazy val peopleCopy = people
    println(s"People first time ${peopleCopy}")
    println(s"People second time ${peopleCopy}")
  }

  def printPeopleBad2(people : => List[Person]) = {
    val peopleCopy = () => people
    println(s"People first time ${peopleCopy()}")
    println(s"People second time ${peopleCopy()}")
  }

  /*
  There is another way to implement lazy evaluations in Scala. It is through using anonymous functions and taking advantage
  of the fact that functions are a part of unifications in Scala and we can also pass them as parameters easily. This is done
  as follows—a value is represented as () => value rather than just the value itself. It is somewhat pointless, though, especially
  because we already have two mechanisms that can do quite a lot. Using anonymous functions for a lazy evaluation is not recommended.
   */
}

object Example {
  import Person._
  def main(args: Array[String]): Unit = {
    println("Now printing bad ... ")
    printPeopleBad(getPersons)
    println("Now printing good ... ")
    printPeopleGood(getPersons)
    println("Now printing good ... ")
    printPeopleBad2(getPersons)
  }
}

/*
the first version of our method retrieves the by-name parameter value twice, while the second version does it only once.

The fact that we use a lazy val inside the second method also has the possibility of not evaluating our expensive expression
at all if we don't actually use it.
 */