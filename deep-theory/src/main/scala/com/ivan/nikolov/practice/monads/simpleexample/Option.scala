package com.ivan.nikolov.practice.monads.simpleexample

import com.ivan.nikolov.practice.monads.Monad

sealed trait Option[A] extends Monad[A]

case class Some[A](a : A) extends Option[A] {
  override def unit[Y](value: Y): Monad[Y] = Some(value)

  override def flatMap[Y](f: A => Monad[Y]): Monad[Y] = f(a)
}

case class None[A]() extends Option[A] {
  override def unit[Y](value: Y): Monad[Y] = None()

  override def flatMap[Y](f: A => Monad[Y]): Monad[Y] = None()
}