package com.ivan.nikolov.practice.monads

trait Functor[T] {
  def map[Y](f : T => Y) : Functor[Y]
}

trait Monad[T] extends Functor[T] {

  def unit[Y](value : Y) : Monad[Y]

  def flatMap[Y](f : T => Monad[Y]) : Monad[Y]

  override def map[Y](f: T => Y):  Monad[Y] = flatMap(x => unit(f(x)))

}
