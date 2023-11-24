package com.learning.functions

object FunctionWithVarArgs extends App {
  def sum(nums: Int*): Int = {
    println(nums.getClass)
    nums.sum
  }

  val arr: Array[Int] = Array[Int](2, 3, 4, 5, 6)
  println(sum(arr:_*))
  println(sum(4, 3, 4, 10, 8))
}
