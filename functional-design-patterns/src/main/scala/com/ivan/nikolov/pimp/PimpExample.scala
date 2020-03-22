package com.ivan.nikolov.pimp

import com.ivan.nikolov.pimp.model.Person
//This is seen quite often, especially when a decorator or adapter design pattern is needed.
object PimpExample {

  def main(args: Array[String]): Unit = {
    System.out.println(s"Is 'test' all upper case: ${"test".isAllUpperCase}")
    System.out.println(s"Is 'Tes' all upper case: ${"Test".isAllUpperCase}")
    System.out.println(s"Is 'TESt' all upper case: ${"TESt".isAllUpperCase}")
    System.out.println(s"Is 'TEST' all upper case: ${"TEST".isAllUpperCase}")
  }
}

//We can also apply the pimp my library design pattern to our custom classes if we need to and if it makes sense.

/*
 Additionally, the decorator design pattern will suffer in the cases where the class we are trying to decorate is final.
 Here, there is no issue. Again, all the magic happens because we have an implicit class, and the Scala compiler automatically
 figures out that it can wrap and unwrap a string depending on the methods we call on it.
 */

object PimpExample2 {
  def main(args: Array[String]): Unit = {
    val people = List(
      Person("Ivan", 26),
      Person("Maria", 26),
      Person("John", 25)
    )
    people.saveToDatabase()
  }
}
