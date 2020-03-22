package com.ivan.nikolov.practice.monads.simpleexample

import com.ivan.nikolov.practice.monads.Monad

//1. we create an ADT
//2. we create out types that will extend the ADT
//3. we implement the unit, map and flatMap methods

/*
trait Functor[T] {
  def map[Y](f : T => Y) : Functor[Y]
}

trait Monad[T] extends Functor[T] {

  def unit[Y](value : Y) : Monad[Y]

  def flatMap[Y](f : T => Monad[Y]) : Monad[Y]

  override def map[Y](f: T => Y):  Monad[Y] = flatMap(x => unit(f(x)))

}
 */

sealed trait Option[A] extends Monad[A]

case class Some[A](a : A) extends Option[A] {
  override def unit[Y](value: Y): Monad[Y] = Some(value)

  override def flatMap[Y](f: A => Monad[Y]): Monad[Y] = f(a)
}

case class None[A]() extends Option[A] {
  override def unit[Y](value: Y): Monad[Y] = None()

  override def flatMap[Y](f: A => Monad[Y]): Monad[Y] = None()
}

case class Implementation_v1(left : Int, right : Int) {
  def compute = left + right
}

case class Algorithm_v1() {
  def getImplementation(isFail : Boolean, right : Int, left : Int) : Option[Implementation_v1] = {
    if (isFail) {
      None()
    } else {
      Some(Implementation_v1(left, right))
    }
  }
}


case class Doer_v1() {
  def getAlgorithm(isFail : Boolean) : Option[Algorithm_v1] = {
    if (isFail) {
      None()
    } else {
      Some(Algorithm_v1())
    }
  }
}

/*
We made our code much more straightforward, and we abstracted some logic inside the monads. Also, our code became much more readable than it was before.
 */

object MonadExample {
  def main(args: Array[String]): Unit = {
    println(s"The result is ${compute(Some(Doer_v1()), 10, 15)}")
    println(s"The result is ${compute1(Some(Doer_v1()), 10, 15)}")
    println(s"The result is ${compute1(None(), 10, 15)}")
  }

  def compute(doer : Option[Doer_v1], left : Int, right : Int) = {
    for {
      d <- doer
      algorithm <- d.getAlgorithm(false)
      implementation <- algorithm.getImplementation(false, left, right)
    } yield implementation.compute
  }

  def compute1(doer : Option[Doer_v1], left : Int, right : Int) = {
    doer.flatMap(
      x => x.getAlgorithm(false).flatMap(
        x => x.getImplementation(false, left, right).map(
          x => x.compute
        )
      )
    )
  }
}