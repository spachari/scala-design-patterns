package com.ivan.nikolov.practice.monads.IO

/*
We need to make sure that our application has only one state and that nobody can create a state whenever they want. The fact that the
trait is sealed helps us to make sure nobody can extend our state outside the file, where we have defined it. Being sealed is not enough, though.

The preceding code defines the state as a private class, and this means that nobody else will be able to create one.

The third rule we defined for our state earlier is much trickier to achieve. We have taken multiple steps in order to make sure the state behaves correctly.
First of all, there is no clue of a state that the user can get to, except the private class that nobody can instantiate.
 */

sealed trait State1 {
  def next : State1
}

abstract class FileIO1 {
  // this makes sure nobody can create a state
  private class FileIOState(id: Int) extends State1 {
    override def next: State1 = new FileIOState(id + 1)
  }

  def run(args: Array[String]): Unit = {
    val action : IOAction1[_] = runIO(args(0), args(1))
    action(new FileIOState(0))
  }

  def runIO(readPath: String, writePath: String): IOAction1[_]
}

/*
We haven't specifically extended our Monad trait, but instead we have just defined the methods here. We already know that map can be defined using
flatMap and unit. For the latter, we have used the factory method for the SimpleAction.
 */

sealed abstract class IOAction1[T] extends ((State1) => (State1,T)) {
  def unit[Y](value : Y) : IOAction1[Y] =  IOAction1.unit(value)

  def flatMap[Y](f : (T) => IOAction1[Y]) : IOAction1[Y] = {
    val self = this
    new IOAction1[Y] {
      override def apply(state: State1): (State1, Y) = {
        val (state2, res) = self(state)
        val action2 = f(res)
        action2(state2)
      }
    }
  }

  def map[Y](f : T => Y) : IOAction1[Y] = flatMap(i => unit(i))
}



object IOAction1 {
  def apply[T](result : => T): IOAction1[T] = new SimpleIOAction[T](result)

  def unit[T](value : T) : IOAction1[T] = new EmptyAction[T](value)

  private class SimpleIOAction[T](result : => T) extends IOAction1[T] {
    override def apply(state: State1): (State1, T) = (state.next, result)
  }

  private class EmptyAction[T](value : T) extends IOAction1[T] {
    override def apply(state : State1) : (State1, T) = (state, value)
  }


}

object FileIOExample extends FileIO1 {

  def main(args: Array[String]): Unit = {
    run(args)
  }

  override def runIO(readPath: String, writePath: String): IOAction1[_] =
    for {
      lines <- readFile(readPath)
      _ <- writeFile(writePath, lines.map(_.toUpperCase))
    } yield ()
}
