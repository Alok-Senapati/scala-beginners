package com.learning.pattern_matching

import com.learning.exercises.GenericCovariantList.{ConsGenericList, EmptyGenericList, GenericList}

object AllThePatterns extends App {

  // 1. Constants - Value Matching
  val x: Any = "Scala"
  val constants = x match {
    case 1 => "One"
    case "Scala" => "Scala"
    case true => "True"
    case AllThePatterns => "SingletonObject"
    case _ => "SomethingElse"
  }
  println(constants)

  // 2. Match Anything
  // 2.1. Wildcard _
  x match {
    case _ => println("Anything")
  }

  // 2.2. Variable
  x match {
    case something => println(s"Something: $something")
  }

  // 3. Tuples
  val aTuple = (2, 4)
  aTuple match {
    case (1, x) => println(s"Tuple: (1, $x)")
    case (x, 3) => println(s"Tuple: ($x, 3)")
    case (x, y) => println(s"Tuple: ($x, $y)")
  }

  val nestedTuple = (1, (2, "Value2"))
  nestedTuple match {
    case (1, (x, "Value")) => println(x)
    case(x, (y, z)) => println(s"($x, ($y, $z))")
    case x -> y => println(s"$x -> $y")
  }

  // 4. Case Classes
  val aConsGenericList: GenericList[Int] = ConsGenericList[Int](1, ConsGenericList(2, ConsGenericList(3, ConsGenericList(4, EmptyGenericList))))
  aConsGenericList match {
    case EmptyGenericList => println("EmptyGenericList")
    case ConsGenericList(head, ConsGenericList(subhead, subtail)) => println(s"$head -> $subhead -> $subtail")
    case ConsGenericList(head, tail) => println(s"$head -> $tail")
  }

  // 5 - List patterns
  val aStandardList = List(1, 2, 3, 42)
  aStandardList match {
    case List(1, x, y, _) => println(s"List(1, $x, $y, ... )")
    case List(1, _, _, _) => println(s"List starts with 1 with length 4")// extractor - advanced
    case List(1, _*) => println(s"List starts with 1") // list of arbitrary length - advanced
    case head :: tail => println(s"$head -> $tail") // infix pattern
    case List(1, 2, _) :+ 42 => "lala" // infix pattern
  }

  // 6 - type specifiers
  val unknown: Any = 2
  unknown match {
    case x: Int => println(s"$x is an Integer")
    case s: String => println(s"$s is a String")
    case list: List[Int] => println(s"$list is a List")// explicit type specifier
    case _ => println(s"Other Type")
  }

  // 7 - Name Binding
  aConsGenericList match {
    case ConsGenericList(1, rest@ConsGenericList(2, _)) => println(s"$rest")// name binding inside nested patterns
    case nonEmptyList @ ConsGenericList(_, _) => println(s"$nonEmptyList")// name binding => use the name later(here)
  }

  // 8 - Multi Pattern
  val anotherConsGenericList: GenericList[Int] = ConsGenericList(0, EmptyGenericList) // EmptyGenericList
  anotherConsGenericList match {
    case EmptyGenericList | ConsGenericList(0, _) => println(s"Empty or starts with 0")// compound pattern (multi-pattern)
  }

  // 9 - if guards
  aConsGenericList match {
    case ConsGenericList(_, ConsGenericList(specialElement, _)) if specialElement % 2 == 0 => println(s"$specialElement")
  }
  /*
    Question.
   */

  val numbers = List(1, 2, 3)
  val numbersMatch = numbers match {
    case listOfStrings: List[String] => "a list of strings"
    case listOfNumbers: List[Int] => "a list of numbers"
    case _ => ""
  }

  println(numbersMatch) // matches List[String] as it comes first
  // JVM trick question
  // JVM is build in such a way that it is backward compatible
  // The code written in Java 1 can run in Java 9
  // In Java 1, The Concept of Generics were not there
  // So while pattern matching List[AnyType] matches to List
  // This is called type erasure
}
