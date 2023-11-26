package com.learning.exercises

object ExceptionsExercise extends App {
  /* Exercises -
      1. Crash your program with an OutOfMemory Error
      2. Crash your program with an StackOverflow Error
      3. PocketCalculator:
        - add(x, y)
        - subtract(x, y)
        - multiply(x, y)
        - divide(x, y)

        Throws
          - OverflowException if add(x, y) exceeds Int.MAX_VALUE
          - UnderflowException if subtract(x, y) goes below Int.MIN_VALUE
          - MathCalculatingException from division by 0
  */

  // val array = Array.ofDim[Int](Int.MaxValue) // Will throw OutOfMemory Error

  // Below will throw StackOverflow Error
  /*
    def infinite: Int = 1 + infinite
    infinite
  */

  class OverflowException extends RuntimeException
  class UnderflowException extends RuntimeException
  class MathException extends RuntimeException("DivisionByZero")

  object PocketCalculator {
    def add(x: Int, y: Int): Int = {
      val result = x + y
      if x > 0 && y > 0 && result < 0 then throw new OverflowException
      else if x < 0 && y < 0 && result > 0 then throw new UnderflowException
      else result
    }

    def subtract(x: Int, y: Int): Int = {
      val result = x - y
      if x > 0 && y < 0 && result < 0 then throw new OverflowException
      else if x < 0 && y > 0 && result > 0 then throw new UnderflowException
      else result
    }

    def multiply(x: Int, y: Int): Int = {
      val result = x * y
      if (x > 0 && y > 0 && result < 0) || (x < 0 && y < 0 && result <= 0) then throw new OverflowException
      else if (x > 0 && y < 0 && result >= 0) || (x < 0 && y > 0 && result >= 0) then throw new UnderflowException
      else result
    }

    def divide(x: Int, y: Int): Int = {
      if y == 0 then throw new MathException
      else x / y
    }
  }

  // println(PocketCalculator.add(Int.MaxValue, 10)) // OverflowException
  // println(PocketCalculator.add(Int.MinValue, -10)) // UnderflowException
  println(PocketCalculator.add(10, 10))

  // println(PocketCalculator.subtract(Int.MaxValue, -10)) // OverflowException
  // println(PocketCalculator.subtract(Int.MinValue, 10)) // UnderflowException
  println(PocketCalculator.subtract(30, 10))

  // println(PocketCalculator.multiply(Int.MaxValue, 2)) // OverflowException
  // println(PocketCalculator.multiply(Int.MinValue, -2)) // OverflowException
  // println(PocketCalculator.multiply(Int.MaxValue, -2)) // UnderflowException
  // println(PocketCalculator.multiply(Int.MinValue, 2)) // UnderflowException
  println(PocketCalculator.multiply(10, 30))

  println(PocketCalculator.divide(50, 2))
  // println(PocketCalculator.divide(12, 0)) // MathException

}
