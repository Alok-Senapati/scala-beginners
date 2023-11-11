package com.learning.functions

object Functions extends App {
  // Function with Parameters
  def sum(a: Int, b: Int): Int = a + b
  println(sum(5, 10))

  // Function without any parameters
  def printSomething(): Unit = {
    println("Something")
  }
  printSomething()

  // We can skip the parenthesis in function definition and call for functions with no param
  def printScala = {
    println("Scala")
  }
  printScala


}
