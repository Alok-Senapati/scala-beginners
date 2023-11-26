package com.learning.oop // Not an expression

import com.learning.exercises.ExceptionsExercise.{
  PocketCalculator => Calculator  // Import with an alias
}

object PackageAndImports extends App {
  println(Calculator.add(10, 20))
  println(SPEED_OF_LIGHT) // As present in same package
  println(com.learning.oop.SPEED_OF_LIGHT) // Defined constant in oop package object
}
