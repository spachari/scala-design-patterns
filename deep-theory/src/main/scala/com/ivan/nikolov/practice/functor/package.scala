package com.ivan.nikolov.practice

package object functor {

  val listFunctors = new Functor[List] {
    override def map[T, Y](list: List[T])(f: T => Y): List[Y] = list.map(f)
  }

}
