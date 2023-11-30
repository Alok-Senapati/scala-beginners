package com.learning.pattern_matching

object PatternsEverywhere extends App {

  // Big Idea #1
  try {
    // code
  } catch {
    case e: RuntimeException => "runtime"
    case npe: NullPointerException => "npe"
    case _ => "something else"
  }

  // catches are actually MATCHES
  /*
    try {
      // code
    } catch (e) {
      e match {
        case e: RuntimeException => "runtime"
        case npe: NullPointerException => "npe"
        case _ => "something else"
      }
    }
   */

  // Big Idea #2
  val list = List(1,2,3,4)
  val evenOnes = for {
    x <- list if x % 2 == 0 // ?!
  } yield 10 * x
  println(evenOnes)

  // Generators are also based on PATTERN MATCHING
  val tuples = List((1,2), (3,4))
  val filterTuples = for {
    (first, second) <- tuples
  } yield first * second
  println(filterTuples)
  // case classes, :: operators, ...

  // Big Idea #3
  val tuple = (1,2,3)
  val (a, b, c) = tuple
  println(b)
  // multiple value definitions based on PATTERN MATCHING

  val head :: tail = list
  println(head)
  println(tail)

  // Big Idea #4
  // Partial function based on PATTERN MATCHING
  val mappedList = list.map {
    case v if v % 2 == 0 => v + " is even"
    case 1 => "the one"
    case _ => "something else"
  } // partial function literal
  // Same as
  val mappedList2 = list.map { x => x match {
    case v if v % 2 == 0 => v + " is even"
    case 1 => "the one"
    case _ => "something else"
  }
  }
  println(mappedList)

}
