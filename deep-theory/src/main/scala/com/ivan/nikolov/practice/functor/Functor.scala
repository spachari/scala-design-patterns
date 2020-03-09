package com.ivan.nikolov.practice.functor

trait Functor[F[_]] {
  def map[T,Y](list : F[T])(f : T => Y) : F[Y]
}


