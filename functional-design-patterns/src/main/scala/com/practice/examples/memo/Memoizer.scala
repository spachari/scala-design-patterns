package com.practice.examples.memo

import java.security.MessageDigest

import org.apache.commons.codec.binary.Hex
import scalaz.Memo

import scala.collection.mutable.Map



//our own version of memoize
trait Memoizer {
  def memo[X,Y](f : X => Y) : (X => Y) = {   //It takes a function of f : X => Y
    val cache = Map[X, Y]()                  //Create a default map object with the source and target
    (x : X) => cache.getOrElseUpdate(x, f(x))      //
  }
}

class Hasher extends Memoizer {

  def md5(input : String) = {
    println(s"Calling md5 for ${input}")
    new String(Hex.encodeHex(MessageDigest.getInstance("MD5").digest(input.getBytes())))
  }

  //val memoMd5 = memo(md5)


  //using scalaz version of Memo
  val memoMd5Scalaz: String => String = Memo.mutableHashMapMemo {
    md5
  }

}

