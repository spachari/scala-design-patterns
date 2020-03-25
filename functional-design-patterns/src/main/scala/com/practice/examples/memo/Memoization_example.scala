package com.practice.examples.memo


object Memoization_example  {
  def main(args: Array[String]): Unit = {
    val hasher = new Hasher

    println(s"MD5 for 'hello' is '${hasher.memoMd5Scalaz("hello")}'.")
    println(s"MD5 for 'bye' is '${hasher.memoMd5Scalaz("bye")}'.")
    println(s"MD5 for 'hello' is '${hasher.memoMd5Scalaz("hello")}'.")
    println(s"MD5 for 'bye1' is '${hasher.memoMd5Scalaz("bye1")}'.")
    println(s"MD5 for 'bye' is '${hasher.memoMd5Scalaz("bye")}'.")
  }

}
