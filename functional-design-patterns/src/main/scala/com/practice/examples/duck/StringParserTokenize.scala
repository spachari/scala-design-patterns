package com.practice.examples.duck

import java.util.StringTokenizer

class StringParserTokenize {
  def parse(sentence : String) : Array[String] = {
    val tokernizer = new StringTokenizer(sentence)
    Iterator.continually({
      val hasMore = tokernizer.hasMoreTokens
      if (hasMore) {
        (hasMore, tokernizer.nextToken())
      } else {
        (hasMore, null)
      }
    }).takeWhile(_._1).map(_._2).toArray
  }
}
