package com.learning.fp

object AnonymousFunctions extends App {
  // Let's consider below function
  val doubler = new Function1[Int, Int] {
    override def apply(v1: Int): Int = v1 * 2
  }
  // The above code is still a Object Oriented Way of Defining functions.
  // We can write the same code as below
  val doubler1: Int => Int = (x: Int) => x * 2
  // The above line of code is an example of Anonymous Function or LAMBDA function

  // Multiple Params in Lambda
  val adder: (Int, Int) => Int = (x: Int, y: Int) => x + y

  // No Params in Lambda
  val doSomething: () => Int = () => 3
  println(doSomething) // Function Itself
  println(doSomething()) // Function Call
  // For Lambda functions parenthesis is necessary to call the function with no params

  // Curly braces with Lambda
  val subtract = { (x: Int, y: Int) =>
    x - y
  }
  val square = (x: Int) => {
    println(x)
    x * x
  }

  // Syntactic Sugar
  val incrementer: Int => Int = _ + 1 // same as (x: Int) => x + 1
  val multiplier: (Int, Int) => Int = _ * _ // same as (x: Int, y: Int) => x * y
  // But for the above syntactic sugar, we must specify the function type

  /*
    Exercises -
    1. Change GenericCovariantList to take Lambda instead of FunctionX
    2. Implement superAdder from previous exercise (WhatIsFunction) using Lambda
   */

  // 1. Check the com.learning.exercises.GenericCovariantList
  // 2. Solution:
  val superAdder: (Int) => Int => Int = (x: Int) => (y: Int) => x + y
  val adder3 = superAdder(3)
  println(adder3(4))
  println(superAdder(3)(4))

}
