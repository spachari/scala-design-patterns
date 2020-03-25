package com.practice.examples.cake

import com.practice.examples.cake.model.Person
import org.scalatest.{FlatSpec, Matchers}
import org.scalatest.mock.MockitoSugar

trait TestEnvironment extends UserComponent with DaoComponent with DatabaseComponent with MigrationComponent with MockitoSugar {

  override val dao : Dao = mock[Dao]
  override val databaseService: DatabaseService = mock[DatabaseService]
  override val migrationService: MigrationService = mock[MigrationService]
  override val userService: UserService = mock[UserService]

}

/*
The preceding code simply contains every component and mocks every service with Mockito. Let's write a test
class for our UserComponent using our new test environment:
 */

/*
class UserComponent extends FlatSpec with Matchers with MockitoSugar with TestEnvironment {
  val className = "A"
  val emptyClassName = "B"

  val people = List(
    Person(1, "a", 10),
    Person(2, "b", 15),
    Person(3, "c", 20)
  )

  override val userService = new UserService


  assert(dao.getPeopleInClass(className) == people)
  assert(dao.getPeopleInClass(emptyClassName) == List())

  "getAverageAgeOfUsersInClass" should "properly calculate the average of all ages." in {
    userService.getAverageAgeOfusersInClass(className) should equal(15.0)
  }

  it should "properly handle an empty result." in {
    userService.getAverageAgeOfusersInClass(emptyClassName) should equal(0.0)
  }


}

 */