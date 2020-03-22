package com.practice.examples.cake

import java.sql.Connection

import com.practice.examples.cake.model.{Classes, PeopleToClass, Person}

trait MigrationComponent {
  this : DatabaseComponent =>

  val migrationService : MigrationService // self type annotation
                                          // it tells the compiler that whenever we mix in the MigrationComponent, we also need to mix in the DatabaseComponent.
                                          //this is exactly the piece of the puzzle that tells Scala that the migration component will depend on the database component.
                                          //And if we look carefully, it actually accesses databaseService, which is a part of DatabaseComponent.


  class MigrationService() {
    def runMigrations(): Unit = {
      val connection = databaseService.getConnection
      try {
        // create the database
        createPeopleTable(connection)
        createClassesTable(connection)
        createPeopleToClassesTable(connection)
        // populate
        insertPeople(
          connection,
          List(
            Person(1, "Ivan", 26),
            Person(2, "Maria", 25),
            Person(3, "John", 27)
          )
        )
        insertClasses(
          connection,
          List(
            Classes(1, "Scala Design Patterns"),
            Classes(2, "Java Programming"),
            Classes(3, "Mountain Biking")
          )
        )
        signPeopleToClasses(
          connection,
          List(
            (1, 1),
            (1, 2),
            (1, 3),
            (2, 1),
            (3, 1),
            (3, 3)
          )
        )
      } finally {
        connection.close()
      }
    }

    private def createPeopleTable(connection: Connection): Unit = {
      val statement = connection.prepareStatement(
        """
          |CREATE TABLE people(
          | id INT PRIMARY KEY,
          | name VARCHAR(255) NOT NULL,
          | age INT NOT NULL
          |)
        """.stripMargin
      )
      try {
        statement.executeUpdate()
      } finally {
        statement.close()
      }
    }

    private def createClassesTable(connection: Connection): Unit = {
      val statement = connection.prepareStatement(
        """
          |CREATE TABLE classes(
          | id INT PRIMARY KEY,
          | name VARCHAR(255) NOT NULL,
          |)
        """.stripMargin
      )
      try {
        statement.executeUpdate()
      } finally {
        statement.close()
      }
    }

    private def createPeopleToClassesTable(connection: Connection): Unit = {
      val statement = connection.prepareStatement(
        """
          |CREATE TABLE people_classes(
          | person_id INT NOT NULL,
          | class_id INT NOT NULL,
          | PRIMARY KEY(person_id, class_id),
          | FOREIGN KEY(person_id) REFERENCES people(id) ON DELETE CASCADE ON UPDATE CASCADE,
          | FOREIGN KEY(class_id) REFERENCES classes(id) ON DELETE CASCADE ON UPDATE CASCADE
          |)
        """.stripMargin
      )
      try {
        statement.executeUpdate()
      } finally {
        statement.close()
      }
    }

    private def insertPeople(connection: Connection, people: List[Person]): Unit = {
      val statement = connection.prepareStatement(
        "INSERT INTO people(id, name, age) VALUES (?, ?, ?)"
      )
      try {
        people.foreach {
          case person =>
            statement.setInt(1, person.id)
            statement.setString(2, person.name)
            statement.setInt(3, person.age)
            statement.addBatch()
        }
        statement.executeBatch()
      } finally {
        statement.close()
      }
    }

    private def insertClasses(connection: Connection, classes: List[Classes]): Unit = {
      val statement = connection.prepareStatement(
        "INSERT INTO classes(id, name) VALUES (?, ?)"
      )
      try {
        classes.foreach{
          case cls =>
            statement.setInt(1, cls.id)
            statement.setString(2, cls.name)
            statement.addBatch()
        }
        statement.executeBatch()
      } finally {
        statement.close()
      }
    }

    private def signPeopleToClasses(connection: Connection, peopleToClasses: List[(Int, Int)]): Unit = {
      val statement = connection.prepareStatement(
        "INSERT INTO people_classes(person_id, class_id) VALUES (?, ?)"
      )
      try {
        peopleToClasses.foreach {
          case (personId, classId) =>
            statement.setInt(1, personId)
            statement.setInt(2, classId)
            statement.addBatch()
        }
        statement.executeBatch()
      } finally {
        statement.close()
      }
    }
  }

}
