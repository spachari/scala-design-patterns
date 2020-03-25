package com.practice.examples.implicits

import com.practice.examples.implicits.di.model.DatabaseServiceImpl


package object di {
  implicit val databaseService = new DatabaseServiceImpl
  implicit val userService = new UserServiceImpl
}
