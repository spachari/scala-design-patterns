package com.ivan.nikolov.implicits.di

trait UserService {
  def getAverageAgeOfPeople()(implicit ds: DatabaseService): Double
}

class UserServiceImpl extends UserService {
  def getAverageAgeOfPeople()(implicit ds: DatabaseService): Double = {
    val (s, c) = ds.getPeople().foldLeft((0, 0)) {
      case ((sum, count), person) =>
        (sum + person.age, count + 1)
    }
    s.toDouble / c.toDouble
  }
}

