package com.learning.functions

import scala.annotation.tailrec

object DefaultAndNamesArgs extends App {
  @tailrec
  def factorial(n: Int, acc: BigInt): BigInt = {
    if n <= 1 then acc
    else factorial(n - 1, acc * n)
  }
  println(factorial(10, 1))

  // For the above tail-recursive function we pass 2 parameters: n and acc
  // But for the factorial implementation the input should be only the number
  // To achieve this we can wrap the function in one more function
  // One more way is to use default value in function signature
  @tailrec
  def factorialWithDefaultArgument(n: Int, acc: BigInt = 1): BigInt = {
    if n <= 1 then acc
    else factorialWithDefaultArgument(n - 1, acc * n)
  }
  println(factorialWithDefaultArgument(10))

  // Parameters with default values should always be at last of the function signature
  // Or Else, we should use parameter names to pass values while calling the function
  // When we use named parameters in function call the sequence of parameters does not matter
  def printArgs(s: String = "Something", x: Int, y: Int): Unit = {
    println(s + " " + x + " " + y)
  }
  printArgs(y=10, x=20)
}
