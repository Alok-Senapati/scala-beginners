package com.learning.fp

object WhatIsFunctions extends App {
  // In Functional Programming, We want to use functions as first class programming elements. i.e.
  // We want to pass functions as parameters
  // Use functions as values

  // Problem - Scala is a JVM based language which (JVM) was never designed for functional programming.
  // JVM was designed for Java which is based on OOP, where the first class programming components are Objects

  // Solution - Solution to the above problem is,
  // In Scala all the functions are objects ob special kind of types/traits
  // As we have seen earlier in OOP. An Object can be called as a function when the apply method is overridden
  // Scala uses the same principal and have created Function traits using which we can create function object by implementing the abstract apply method
  // In Scala, we have Function traits having up to 22 parameters
  /* Example: Function1
    trait Function1 [-A, +B] {
      def apply(element: A): B
    }
  */

  // A trait FunctionX takes X+1 Generic types where first X types are types of the parameters passed to the apply method
  // The last type is the return type of the apply method
  val doubler: Int => Int = new Function1[Int, Int] {
    override def apply(v1: Int): Int = v1 * 2
  }
  println(doubler(4))

  val stringToInt: String => Int = new Function[String, Int] {
    override def apply(v1: String): Int = v1.toInt
  }
  println(stringToInt("2") + 3)

  val adder: (Int, Int) => Int = new Function2[Int, Int, Int] {
    override def apply(v1: Int, v2: Int): Int = v1 + v2
  }
  println(adder(10, 30))

  // Syntactic Sugar for Functions
  // The type for Function1[Int, Int] can be written as (Int) => Int or Int => Int
  // Similarly for Function2[Int, Int, String] can we written as (Int, Int) => String and so on
  // Function2[A, B, R] is same as (A, B) => R


  // Exercise:
  // 1. A function that takes 2 strings and concatenate them
  val concatenateStrings = new Function2[String, String, String] {
    override def apply(v1: String, v2: String): String = v1 + v2
  }
  println(concatenateStrings("Hello ", "Scala"))
  // 2. Replace MyTransformer and MyPredicate in GenericCovariantList to use Function
  // Check com.learning.exercises.GenericCovariantList for solution
  // 3. Define a function which takes an Int and returns a Function that takes an Int and returns an Int
  val superAdder = new Function1[Int, Function1[Int, Int]] {
    override def apply(v1: Int): Function1[Int, Int] = new Function1[Int, Int] {
      override def apply(v2: Int): Int = v1 + v2
    }
  }
  val adder2 = superAdder(3)
  println(adder2(4))
  println(superAdder(3)(4)) // This is also known as function currying


}
