package com.learning.oop

import scala.annotation.targetName
import scala.language.postfixOps

object ScalaSyntacticSugars extends App {
  class Person(val name: String, favouriteMovie: String, val age: Int) {
    def likes(movieName: String): Boolean = movieName == favouriteMovie
    @targetName("friendsWith")
    def +(person: Person): String = s"${this.name} is friends with ${person.name}"
    @targetName("exclamationPerson")
    def unary_! : String = s"What the heck, ${this.name}!!"
    def isAlive: Boolean = true
    def apply(): String = s"Hello, I'm $name"
    @targetName("withNickName")
    def +(nickName: String) = new Person(s"${this.name} ($nickName)", this.favouriteMovie, this.age)
    @targetName("incrementAge")
    def unary_+ : Person = new Person(this.name, this.favouriteMovie, this.age + 1)
    private def learns(skill: String): String = s"${this.name} learns $skill"
    def learnScala: String = learns("Scala")
    def apply(watchTimes: Int): String = s"$name has watched $favouriteMovie $watchTimes times"
  }

  private val mary = new Person("Mary", "Inception", 25)
  println(mary.likes("Inception"))
  // same as
  println(mary likes "Inception") // Only works with methods with only 1 parameter
  // The above is called infix notation/operator notation/syntactic sugar

  // Operators in Scala are methods
  var x = 2 + 3
  println(x)
  // same as
  x = 2.+(3)
  println(x)

  private val john = new Person("John", "Interstellar", 34)
  println(mary + john)
  println(mary.+(john))


  // prefix notation
  val y = -1
  // same as
  val z = 1.unary_-
  // applicable for -, +, !, ~

  println(!mary) // same as
  println(mary.unary_!)

  // method with no parameter - postfix notation
  println(mary.isAlive) // same as
  println(mary isAlive)

  // implemented apply method
  println(mary.apply()) // same as
  println(mary())


  // Exercises -->
  // 1. Overload operator +
  println((mary + "the rockstar").name)

  // 2. Add an age to Person class and add prefix + to increment age
  println((+mary).age)

  // 3. Add postfix operator learnScala
  println(mary learnScala)

  // 4. overload apply to print no of times the movie is watched
  println(mary(2)) // same as
  println(mary.apply(2))
}
