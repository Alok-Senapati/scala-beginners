package com.learning.basics

object VariablesTypesValues extends App{
  // val - immutable, var - mutable
  val x = 10 // Scala determines the type automatically
  val y: Int = 10 // Explicit type declaration

  // Numeric Data Types
  val aByte: Byte = 30
  val aShort: Short = 20
  val aInt: Int = 20
  val aLong: Long = 75678576578896585L
  val aDouble: Double = 65.3867
  val aFloat: Float = 65.897f
  
  // String Types
  val aChar: Char = 'a'
  val aString: String = "Hello, Scala"
  
  // Boolean Type
  val aTrueBool: Boolean = true
  val aFalseBool: Boolean = false
  val someArray = Array(1, 3, 2)
  println(someArray.toList)
  val arr = someArray :+ 5
  println(arr.toList)

  // Code block
  val aCodeBlock = { // Stores the value of the last expression of the CodeBlock
    println("Inside a CodeBlock")
    5 + 2
  }
  println(aCodeBlock)
}
