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

  // Parameterless functions
  // Parameterless functions that doesn't return Unit can be defined and called without the parenthesis.
  def getScalaString: String = "This is a Parameterless Function in scala"
  println(getScalaString)

  // Functions returning Unit should be defined with parenthesis. It won't cause error but it's a best practice
  def doSomething(): Unit = { val x = 10 }
  println(doSomething())


}
