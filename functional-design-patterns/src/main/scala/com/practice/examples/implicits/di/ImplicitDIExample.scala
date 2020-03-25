package com.practice.examples.implicits.di

import com.practice.examples.implicits.di.model.DatabaseServiceImpl


object ImplicitDIExample {

  def main(args: Array[String]): Unit = {
    println(s"The average age of the person is ${userService.getAverageAgeOfPeople()(new DatabaseServiceImpl)}")
  }


}
