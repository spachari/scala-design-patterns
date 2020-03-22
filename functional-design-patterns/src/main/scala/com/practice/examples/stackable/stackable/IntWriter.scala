package com.practice.examples.stackable.stackable

import scala.collection.mutable.ArrayBuffer

abstract class IntQueue {
  def get() : Int
  def put (i : Int)
}

class BasicIntQueue extends IntQueue {
  private val buf = new ArrayBuffer[Int]()

  override def get(): Int = buf.remove(0)

  override def put(i: Int): Unit = buf.append(i)

  override def toString: String = buf.toString()
}

trait FilteringIntQueue extends IntQueue {
  abstract override def put(i : Int) = {
    println("Inside filtering Int queue")
    if (i > 0) super.put(i)
  }
}

trait IncrementingIntQueue extends IntQueue {
  abstract override def put( i : Int) = {
    println("Inside incrementing Int queue")
    super.put(i + 1)
  }
}


trait DoublingIntQueue extends IntQueue {
  abstract override def put(i : Int) = {
    println("Inside doubling Int queue")
    super.put(i * 2)
  }
}


object IntWriter extends App {

  //Note that the i is doubled, incremented and filtered and finally added to the queue
  val intQueue = new BasicIntQueue with FilteringIntQueue with IncrementingIntQueue with DoublingIntQueue

  intQueue.put(0)
  println(intQueue.toString)

  intQueue.put(-1)
  println(intQueue.toString)

  intQueue.put(2)
  println(intQueue.toString)

  intQueue.get()

}
