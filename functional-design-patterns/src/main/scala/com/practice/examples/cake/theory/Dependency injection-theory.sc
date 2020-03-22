//So, what exactly is dependency injection? It turns out to be something really simple—every single class that has an object as a parameter
// in their constructor is actually an example of a dependency injection. The reason is that the dependency is injected into the class rather
// than instantiated inside it. Developers should actually try to use this kind of approach instead of creating objects inside a constructor.
// There are many reasons for this, but one of the most important ones is the fact that components can become tightly coupled and practically untestable.

//  Dependency injection, however, could degrade the code quality if implemented using constructor parameters. This would make constructors
//  take a large number of parameters and, as a consequence, it will become really difficult to use the constructors. Of course, using the
//  factory design pattern could help, but there are other approaches that are much more common in enterprise applications. In the following
//  subsections, we will briefly mention the alternatives and show how, using only the features of Scala, we can easily implement dependency injection.