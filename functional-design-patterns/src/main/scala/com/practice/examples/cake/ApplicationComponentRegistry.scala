package com.practice.examples.cake


/*
The nicest part of this is that, if we know that our application will need a UserComponent,
the compiler will tell us that we also need a DaoComponent, and so on, down the chain. The compiler will
basically make sure that we have the entire dependency chain available during compilation, and it won't let
us run our application until we have done things properly. This is extremely useful. In other libraries, this
is not the case and we often find out that our dependency graph is not built properly at runtime. Also, this way
of wiring things up makes sure we have only one instance of each.
 */

object ApplicationComponentRegistry
  extends UserComponent with DaoComponent with DatabaseComponent with MigrationComponent {
  override val userService: ApplicationComponentRegistry.UserService = new UserService
  override val dao: ApplicationComponentRegistry.Dao = new Dao
  override val databaseService: DatabaseService = new H2DatabaseService("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "", "")
  override val migrationService: ApplicationComponentRegistry.MigrationService = new MigrationService
}
