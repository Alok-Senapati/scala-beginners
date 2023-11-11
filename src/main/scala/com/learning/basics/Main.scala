package com.learning.basics

object Main {
  def main(args: Array[String]): Unit = {
    println("Hello World!!")
    def factorial(n: Int): Int =
      if n == 1 then 1 else n * factorial(n-1)
    println(factorial(10))

    val x = 10 + 20 * 100
    lazy val y = 10 + 20 * 100
    println(x)
    println(y)

    val list = for i <- 1 to 10 if i % 2 == 0 yield i
    println(list)
  }
}
