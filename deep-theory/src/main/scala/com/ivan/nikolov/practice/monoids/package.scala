package com.ivan.nikolov.practice

package object monoids {


    val intAddition  = new Monoid[Int] {
      override def op(a: Int, b: Int): Int =  a + b

      override def id: Int = 0
    }

    val intMultiplication = new Monoid[Int] {
      override def id: Int = 1

      override def op(a: Int, b: Int): Int = a * b
    }

    val stringConcatenation = new Monoid[String] {
      override def id: String = ""

      override def op(a: String, b: String): String = a + b
    }


    def compose[T, Y](m1 : Monoid[T], m2 : Monoid[Y]) : Monoid[(T,Y)] = new Monoid[(T,Y)] {
      override def id = (m1.id, m2.id)

      override def op(a: (T, Y), b: (T, Y)): (T, Y) = (m1.op(a._1, b._1), m2.op(a._2, b._2))
    }

  def mapMerge[K,V](a : Monoid[V]) : Monoid[Map[K,V]] = new Monoid[Map[K,V]]{
    override def op(l: Map[K, V], r: Map[K, V]): Map[K, V] = {
      (l.keySet ++ r.keySet).foldLeft(id) {
        (res,key) =>res.updated(key, a.op(l.getOrElse(key, a.id), r.getOrElse(key, a.id)))
      }
    }

    override def id: Map[K, V] = Map()
  }

    object MonoidOperations {
      //def fold[B](list : List[B], m : Monoid[B]) : B = list.foldLeft(m.id)(m.op)

      def fold[A](list : List[A], m : Monoid[A]) : A = foldMap(list, m) (identity)

      def foldMap[T,Y](list : List[T], m : Monoid[Y])(f : T => Y) : Y = list.foldLeft(m.id)((x,y) => m.op(x, f(y)))

      def foldPar[A](list : List[A], m : Monoid[A]) : A = foldMapPar(list, m)(identity)

      def foldMapPar[T,Y](list : List[T], m : Monoid[Y])(f : T => Y) : Y = list.par.foldLeft(m.id)((x,y) => m.op(x,f(y)))

      //parallel computations
      /*
      op(op(op(1, 2), 3), 4) can be computed as op(op(1, 2), op(3, 4)) because of associativity

      Here, the nested operations could be done independently and in parallel. This is also called balanced fold.
       */
      def balancedFold[T,Y](list : IndexedSeq[T], m : Monoid[Y])(f : T => Y) : Y = {
        if (list.length == 0) {
          m.id
        } else if (list.length == 1) {
          f(list(0))
        } else {
          val (left, right) = list.splitAt(list.length / 2)
          m.op(balancedFold(left, m)(f), balancedFold(right, m)(f))
        }
      }


    }
  }