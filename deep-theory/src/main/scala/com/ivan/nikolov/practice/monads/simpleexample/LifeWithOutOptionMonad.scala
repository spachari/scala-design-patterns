package com.ivan.nikolov.practice.monads.simpleexample

case class Implementation(left : Int, right : Int) {
  def compute = left + right
}

case class Algorithm() {
  def getImplementation(isFail : Boolean, left : Int, right : Int) : Implementation = {
    if (isFail) {
      null
    } else {
      Implementation(left, right)
    }
  }
}

case class Doer() {
  def getAlgorithm(isFail : Boolean) : Algorithm = {
    if (isFail) {
      null
    } else {
      Algorithm()
    }
  }
}


object LifeWithOutOptionMonad {
  def main(args: Array[String]): Unit = {
    println(s"The result is ${compute(Doer(), 10, 15)}")
  }

  def compute(doer : Doer, left : Int, right : Int) : Int = {
    if (doer != null) {
      val algorithm = doer.getAlgorithm(false)

      if (algorithm != null) {
        val implementation = algorithm.getImplementation(false, left, right)

        if (implementation != null) {
          implementation.compute
        } else {
          -1
        }
      } else {
        -1
      }
    } else {
      -1
    }
  }
}
