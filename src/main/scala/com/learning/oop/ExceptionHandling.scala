package com.learning.oop

import scala.util.Random

object ExceptionHandling extends App {
  // Throwable class -> Instances of throwable class and subclass can be used with throw statement
  // Scala provides 2 subclasses of Throwable
  // 1. Exception -> Logical Errors in Code. Example: RuntimeException, NullPointerException
  // 2. Error -> System Error while running the code. Example: StackOverflowError

  /*
    val x: String = null
    println(x.length) // will throw NullPointerException
  */

  // throwing an exception =>
  // throwing an exception is an expression that returns Nothing
  // As Nothing is childtype of every other datatype, variables of any datatype can hold instance of Nothing
  // val npeString: String = throw new NullPointerException()  // Will throw a NPE

  // Catching an exception
  def getInt(withException: Boolean): Int =
    if withException then throw RuntimeException("No Int can be returned")
    else Random.nextInt()

  // Try blocks contains the code to be executed
  // Try-Catch-Finally block is also an expression in Scala
  // The return type of the expression depends on the return type of try and catch block only and not on the finally block
  // If return type of both try and catch block is same then the return type of the expression will be the same as try and catch
  // If different, then the return type of the expression will be AnyVal
  try {
    getInt(true)
  } catch {  // Catches any exceptions raised in try block. If the Exception type is not catches the code will fail
    case e: RuntimeException => println(s"Runtime Exception Catched: ${e.getMessage}")
    case e: Exception => println("Some Exception Occurred")
  } finally {  // Optional Block of code that gets executed irrespective of any Exception
    println("Inside Finally Block")
  }


  // Defining custom exceptions
  // Any class that extends Exception class can behave like a custom Exception
  class MyCustomException extends Exception
  try {
    throw new MyCustomException
  } catch {
    case e: MyCustomException => println("Custom Exception")
  }

}
