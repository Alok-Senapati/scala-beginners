package com.learning.strings

import java.util.Properties


object StringExamples extends App {
  System.setProperty("scala.time", "true")
  val str: String = "Hello, I'm learning Scala"
  println(str.charAt(1))
  println(str.substring(0, 5))
  println(str.toUpperCase())
  println(str.toLowerCase())
  println(str.capitalize)

  val sList = str.split(" ").toList
  println(sList)

  // String Functions Only available in Scala
  val aNumberString = "2"
  val num: Int = aNumberString.toInt
  println(num)
  val someString1 = "Hello"
  val someString2 = "Bye"
  println('a' +: someString1) // Prepending a character
  // is same ad
  println(someString1.+:('a'))

  println(someString2 :+ 'z') // Appending a character
  // is same as
  println(someString2.:+('z'))

  println('a' +: someString1 :+ 'z')
  // is same as
  println(someString1.+:('a').:+('z'))

  println(someString1 +: someString2)
  // is same as
  println(someString2.+:(someString1))

  println(someString1 :+ someString2)
  // is same as
  println(someString1.:+(someString1))


  // Interpolation
  // s-Interpolator
  val name = "Alok"
  val age = 25
  println(s"Hello, My name is $name, My age is $age")
  println(s"Hello, My name is $name, My age is ${age+1}")

  // f-Interpolator
  val fruitName = "Apple"
  val price = 25.3
  println(f"Price of $fruitName is $price%5.2f")
  println(f"Price of $fruitName%s is $price%10.2f") // price is prepended with spaces
  // println(f"Price of $fruitName%s is $price%2d") Will throw compilation error as price is double and %2d denotes Int

  // raw-Interpolator
  // Prints raw strings without escaping any escape character
  println(raw"The newline \n is not printed")
  // If the escape character is part of the value of a variable and we print the variable using raw interpolator, then it will get escaped
  val str1 = "The newline \n is printed"
  println(raw"$str1")
}
