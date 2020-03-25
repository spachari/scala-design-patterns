package com.practice.examples.duck


/*
As you can see, both the classes have a parse method with the same signature, but they have no connection with each other.
We, however, want to be able to use them in a method and avoid code duplication. Here is how we can do this:
 */

object DuckTypingExample {

  def parse(sentence : String, parser : {def parse(sentence : String) : Array[String]}) = {
    parser.parse(sentence).foreach(println)
  }

  def main(args: Array[String]): Unit = {
    val tokenizer = new StringParserTokenize
    val parser = new SentenceParserSplit

    val sentence = "This is the sentence we will be splitting."

    println(s"Using the tokenize parser ${parse(sentence, tokenizer)}")
    println(s"Using the string parser ${parse(sentence, parser)}")
  }
}
