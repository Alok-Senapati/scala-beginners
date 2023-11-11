package com.learning.functions.recursion

import scala.annotation.tailrec

object RecursionExamples extends App {
  // Concatenate a String n number of times
  def concatenateString(s: String, n: Int): String = {
    if n <= 1 then s else s + " " + concatenateString(s, n - 1)
  }
  println(concatenateString("Hello", 5))

  // Using Tail Recursion
  def concatStringTR(s: String, n: Int): String = {
    @tailrec
    def concatStringHelper(n: Int, accumulator: String): String = {
      if n < 1 then accumulator else concatStringHelper(n - 1, s + " " + accumulator)
    }
    concatStringHelper(n, "")
  }
  println(concatStringTR("Hello", 5))


  // Calculating nth Fibonacci Number
  def calculateNthFibonacci(n: Int): Int = {
    if n <= 2 then 1 else calculateNthFibonacci(n - 1) + calculateNthFibonacci(n - 2)
  }
  println("10th Fibonacci Number: " + calculateNthFibonacci(10))

  // Using Tail Recursion
  def calculateNthFibonacciTR(n: Int): Int = {
    @tailrec
    def fibonacciHelper(i: Int, last: Int, prevToLast: Int): Int = {
      if i >= n then last
      else fibonacciHelper(i + 1, last + prevToLast, last)
    }
    if n <= 2 then 1
    else fibonacciHelper(2, 1, 1)
  }
  println("10th Fibonacci Number: " + calculateNthFibonacciTR(10))



  // Check if a number is prime
  def isPrime(n: Int): Boolean = {
    @tailrec
    def isStillPrime(i: Int): Boolean = {
      if i <= 1 then true
      else if n % i == 0 then false
      else isStillPrime(i - 1)
    }
    isStillPrime(n / 2)
  }
  println(isPrime(21))
  println(isPrime(3))
  println(isPrime(2))
  println(isPrime(2023))
  println(isPrime(9876))
  println(isPrime(23))
}
