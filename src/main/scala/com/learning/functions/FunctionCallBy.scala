package com.learning.functions

object FunctionCallBy extends App {
  def callByValue(n: Long): Unit = {
    println("Call By Value: " + n)
    println("Call By Value: " + n)
  }

  def callByName(n: => Long): Unit = {
    println("Call By Name: " + n)
    println("Call By Name: " + n)
  }

  // For a function call by value, System.nanoTime() is evaluated first and the value of it is passed to the function parameter
  // For a function call by Name, System.nanoTime() is executed each time the parameter is being used in the function
  callByValue(System.nanoTime())
  callByName(System.nanoTime())


  def infinite(): Int = 1 + infinite()
  def printArg(x: Int, y: => Int): Unit = println(x) // Parameter x in callByValue where as y is callByName

  //  printArg(infinite(), 35) // StackOverFlow as the compiler will try to execute infinite() before passing the result as parameter x
  // Below function call won't throw StackOverflow
  // As the infinite() is passed to a parameter which is callByName, It will only gets executed where ever the parameter y is being used in printArg
  // As y is nowhere used inside printArg, the infinite() will never get called
  printArg(35, infinite())
}
