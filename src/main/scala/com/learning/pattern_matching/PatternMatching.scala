package com.learning.pattern_matching

import scala.util.Random

object PatternMatching extends App {
  val random = new Random
  val x = random.nextInt()

  val description = x match {
    case 1 => "One"
    case 2 => "Two"
    case 3 => "Three"
    case _ => "An Integer"
  }
  println(s"$x, $description")

  // Use cases
  // 1. Decompose Values
  // case classes have the ability to be deconstructed or extracted to be pattern matched
  case class Person(name: String, age: Int)

  val bob = Person("Bob", 20)
  val greeting = bob match {
    case Person(n, a) if a > 18 => s"Hi, I'm $n and I'm an adult."  // Case with condition
    case Person(n, a) => s"Hi I'm $n and I'm $a years old."
    case _ => s"I Don't know who I am"
  }
  println(greeting)

  /*
    PTR about pattern match-case (pattern matching)
      - cases are matched in order
      - If no cases are matched and no default case is there -> throws MatchError
      - Type of the PM Expression -> Unified type of all the types in all the cases
      - PM works really well with pattern matching
   */

  // Sealed classes
  sealed class Animal
  case class Dog(breed: String) extends Animal
  case class Parrot(greet: String) extends Animal

  // When we use sealed parent class. It will raise a warning that all the cases are not covered. In case of normal parent classes it won't
  val animal: Animal = Dog("Terra Nova")
  animal match
    case Dog(breed) => println(s"Matched a Dog with $breed breed")

  animal match { // Not causing warning as all the cases are covered
    case Dog(b) => println(s"Matched a Dog with $b breed")
    case Parrot(g) => println(g)
    case _ => println(s"Matched with an Animal")
  }


  /*
     Exercise
     simple function uses PM
      takes an Expr => human readable form

      Sum(Number(2), Number(3)) => 2 + 3
      Sum(Number(2), Number(3), Number(4)) => 2 + 3 + 4
      Prod(Sum(Number(2), Number(1)), Number(3)) = (2 + 1) * 3
      Sum(Prod(Number(2), Number(1)), Number(3)) = 2 * 1 + 3
    */
  trait Expr
  case class Number(n: Int) extends Expr
  case class Sum(e1: Expr, e2: Expr) extends Expr
  case class Prod(e1: Expr, e2: Expr) extends Expr

  def show(e: Expr): String = e match {
    case Number(n) => n.toString
    case Sum(e1, e2) => s"${show(e1)} + ${show(e2)}"
    case Prod(e1, e2) => {
      def applyParenthesis(exp: Expr): String = exp match {
        case Prod(_, _) => show(exp)
        case Number(_) => show(exp)
        case _ => "(" + show(exp) + ")"
      }

      applyParenthesis(e1) + " * " + applyParenthesis(e2)
    }
  }


  println(show(Sum(Number(2), Number(3))))
  println(show(Sum(Sum(Number(2), Number(3)), Number(4))))
  println(show(Prod(Sum(Number(2), Number(1)), Number(3))))
  println(show(Prod(Sum(Number(2), Number(1)), Sum(Number(3), Number(4)))))
  println(show(Sum(Prod(Number(2), Number(1)), Number(3))))
}
