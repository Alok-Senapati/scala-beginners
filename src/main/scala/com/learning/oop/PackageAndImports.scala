package com.learning.oop // Not an expression

import com.learning.exercises.ExceptionsExercise.{
  PocketCalculator => Calculator  // Import with an alias, Helpful in case of importing 2 classes/methods/objects of same name from different packages
}

object PackageAndImports extends App {
  println(Calculator.add(10, 20))
  println(SPEED_OF_LIGHT) // As present in same package
  println(com.learning.oop.SPEED_OF_LIGHT) // Defined constant in oop package object
  sayHello()
  com.learning.oop.sayHello()

  // Default Imports
  // 1. java.lang -> String, Object, Exception etc.
  // 2. scala -> Int, Nothing, Function etc.
  // 3. scala.Predef -> println, ??? etc.
}
