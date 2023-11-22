package com.learning.functions

object FunctionWithVarArgs extends App {
  def sum(nums: Int*): Int = {
    nums.sum
  }

  println(sum(1, 2, 3, 4, 5))
}
