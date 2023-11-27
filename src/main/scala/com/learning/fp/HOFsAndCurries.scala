package com.learning.fp

import scala.annotation.tailrec

object HOFsAndCurries extends App {
  // HOF(Higher Order Functions) -> Functions that can take other functions as parameters and/or can return a function
  // Examples - map, flatMap, filter in GenericCovariantList
  // Curried Functions -> Functions with multiple parameter list. These are functions that can return functions
  // add(x, y) -> add(x)(y)

  // Let's define a HOF that can apply a function on a parameter n times
  // nTimes(f, n, x) = f(f(f(....nTimes(x))))
  @tailrec
  def nTimes(f: Int => Int, n: Int, x: Int): Int = {
    if n <= 0 then x
    else nTimes(f, n - 1, f(x))
  }

  println(nTimes((a: Int) => a + 1, 10, 1))

  // We can also Implement the method using Currying
  def nTimesCurried(f: Int => Int, n: Int): Int => Int = {
    if n <= 0 then (x: Int) => x
    else (x: Int) => nTimesCurried(f, n - 1)(f(x))
  }
  println(nTimesCurried((a: Int) => a + 1, 10)(1))
  val increment10Times = nTimesCurried(_ + 1, 10)
  println(increment10Times(5))

  // Function with multiple parameter list - Curried functions can also be defined as a function taking multiple parameter list
  def curriedFormatter(format: String)(num: Double) = format.format(num)

  val standardFormatter = curriedFormatter("%4.2f")
  val preciseFormatter = curriedFormatter("%10.8f")
  println(standardFormatter(Math.PI))
  println(preciseFormatter(Math.PI))
}
