package com.practice.examples.cake

import java.sql.Connection

import org.h2.jdbcx.JdbcConnectionPool

/*
preceding code, we used a trait and will extend this trait whenever we want to create an H2 database service, or an Oracle database service, and so on.
 */
trait DatabaseService {
  val DdbDriver : String
  val connectionString : String
  val username : String
  val password : String

  //listing the order of the variable definitions matters. This means that if we had declared ds first and then everything else,
  // we would have faced a NullPointerException. This can be easily overcome using a lazy val instead.
  val ds = {
    JdbcConnectionPool.create(connectionString, username, password)
  }

  def getConnection : Connection = ds.getConnection
}

trait DatabaseComponent {
  val databaseService : DatabaseService

  class H2DatabaseService (val connectionString : String, val username : String, val password : String) extends DatabaseService {
    override val DdbDriver: String = "org.h2.Driver"
  }

}

/*
The actual implementation of the database service is in the nested H2DatabaseService class. Nothing special about it. But what about the DatabaseComponent
trait? It is simple—we want to have a database component that we will mix in our classes and it will provide functionality to connect to the databases.
The databaseService variable is left to be abstract and will have to be implemented when the component is mixed in.
 */