package com.learning.functions.recursion

object RecursionUnitTest {
  def factorial(n: Int, acc: BigInt = 1): BigInt = {
    if n <= 1 then acc
    else factorial(n - 1, n * acc)
  }
}
