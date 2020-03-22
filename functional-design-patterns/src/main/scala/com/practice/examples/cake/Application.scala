package com.practice.examples.cake

object Application {

  import ApplicationComponentRegistry._

  def main(args: Array[String]): Unit = {
    migrationService.runMigrations()
    println(dao.getPeople)
    println(dao.getClasses)
    println(dao.getPeopleInClass("Mountain Biking"))
    val averageAgeOfPeopleInScalaClass = userService.getAverageAgeOfusersInClass("Mountain Biking")
    println(s"Average age of people studying in Scala class is ${averageAgeOfPeopleInScalaClass}")

  }

}
