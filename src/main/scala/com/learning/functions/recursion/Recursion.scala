package com.learning.functions.recursion

import scala.annotation.tailrec

object Recursion extends App {
  // In functional programming loops are avoided as they produce side effect.
  // Recursion is used where ever the use of loop is required.
  // Example: Calculating factorial of a number

  // Using LOOP:
  def factorialUsingLoop(n: Int): BigInt = {
    var factorial: BigInt = 1
    for (i <- 1 to n) {
      factorial = factorial * i
    }
    factorial
  }
  println("Factorial of 10 using Loop: " + factorialUsingLoop(10))

  // Using Recursion:
  // NOTE - Mentioning Function Return Type is mandatory in a recursive function
  //  @tailrec // Compiler will fail as the function is not tail recursive
  def factorialUsingRecursion(n: Int): BigInt = {
    if n <= 1 then 1 else n * factorialUsingRecursion(n - 1)
  }
  println("Factorial of 10 using Recursion: " + factorialUsingRecursion(10))

  // But the draw back of recursion over loop is for a large value of n, the recursion will fail with StackOverflowError
  println("Factorial of 50000 using Loop: " + factorialUsingLoop(50000))
//  println("Factorial of 50000 using Recursion: " + factorialUsingRecursion(50000))

  // To avoid the StackOverflow error we have to make our recursive function tail recursive
  // A Tail Recursive function doesn't need to keep track of the previous recursive call in Stack
  def factorialUsingTailRecursion(n: Int): BigInt = {
    @tailrec // Checks if the function is tail recursive
    def factorialHelper(i: Int, accumulator: BigInt): BigInt = {
      if i <= 1 then accumulator else factorialHelper(i - 1, i * accumulator)
    }
    factorialHelper(n, 1)
  }

  println("Factorial of 50000 using Tail Recursion: " + factorialUsingTailRecursion(50000))
}
