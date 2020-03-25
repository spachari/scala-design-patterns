package com.practice.examples.duck

class SentenceParserSplit {
  def parse(sentence : String) : Array[String] = {
    sentence.split("\\s")
  }
}
