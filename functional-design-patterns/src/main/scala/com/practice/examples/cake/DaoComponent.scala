package com.practice.examples.cake

import java.sql.{PreparedStatement, ResultSet}

import com.practice.examples.cake.model.{Classes, PeopleToClass, Person}

trait DaoComponent {
  this : DatabaseComponent =>

  val dao : Dao

  class Dao() {
    def getPeople : List[Person] = {
      val connection = databaseService.getConnection

      try {
        executeSelect[Person](
          connection.prepareStatement("SELECT id, name, age FROM people")
        ) {
          rs => readResultSet(rs) {
            row =>
              Person(row.getInt(1), row.getString(2), row.getInt(3))
          }
        }
      } finally {
        connection.close()
      }
    }

    def getClasses : List[Classes] = {
      val connection = databaseService.getConnection

      try {
        executeSelect(
          connection.prepareStatement("select id, name from classes")
        ) {
          rs => readResultSet(rs) {
            row =>
              Classes(row.getInt(1), row.getString(2))
          }
        }
      } finally {
        connection.close()
      }
    }

    def getPeopleInClass(classname : String) : List[Person] = {
      val connection = databaseService.getConnection

      try {
          val statement = connection.prepareStatement(
            s"""
               |select p.id, p.name, p.age
               |from
               |people p join people_classes pc on p.id = pc.person_id
               |join classes c on pc.class_id = c.id
               |where c.name = ?
               |""".stripMargin
          )
            statement.setString(1, classname)
        executeSelect(statement)
        {
          rs => readResultSet(rs) {
            row =>
              Person(row.getInt(1), row.getString(2), row.getInt(3))
          }
        }
      }
    }

    private def executeSelect[T](preparedStatement : PreparedStatement)(f : ResultSet => List[T]) : List[T] = {
      try {
        val resultSet : ResultSet = preparedStatement.executeQuery()
        f(resultSet)
      } finally {
        preparedStatement.close()
      }
    }

    private def readResultSet[T](rs : ResultSet)(f : ResultSet => T) = {
      Iterator.continually((rs.next(), rs)).takeWhile(_._1).map{
        case(_, row) =>
          f(rs)
      }.toList
    }
  }

}
