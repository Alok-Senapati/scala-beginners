package com.learning.oop

object Companions extends App {
  // Scala Objects - SINGLETON INSTANCE
  // Scala does not have the concept of static
  // We can define a class and a object of same name and scope. They are called companions
  // The object can contain all the variables and method that are supposed to be static in Java or other languages
  // Both the companion class and object can share each other's private fields and methods
  object Person { // Instance of Type Person referred as Person
    // static/class level functionality
    val N_EYES = 2
    def canFly: Boolean = false
    def apply(mother: Person, father: Person): Person = new Person("Bobbie")
  }

  class Person (name: String) {
    // Instance level functionality

  }

  println(Person.N_EYES)
  println(Person.canFly)

  val p1 = Person
  val p2 = Person
  println(p1 == p2) // returns true as mary and john refers to same instance

  val mary = new Person("Mary")  // When new keyword is used, It creates a new Instance of the companion class
  val john = new Person("john")
  println(mary == john) // return false as both the variables refers to difference instance

  // Scala Applications --> Scala objects with method
  // def main(args: Array[String]): Unit = { ... }
  // or else an object extending App because App implements the main method
}
