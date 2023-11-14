package com.learning.oop

object ClassAndObject extends App {
  val person = new Person // Instantiation of a class using new
  val person1 = new Person1("Alok", 27)
  //  println(person1.name) // Will raise compilation error as name is private instance field
  println(person1.getName)
  println(person1.age)
  println(person1.ageAfterTenYears)
  person1.greet("Priya")
  person1.greet()

  val person2 = new Person1()
  println(person2.getName)

}

class Person // Class without a constructor

/*
  Class with a constructor
  * name and age are constructor parameters
  * age is an public instance field as it is declared with val/var
  * name is just an constructor parameter unless it's being used inside the class
  * If name is getting used in the class then it becomes private instance field
 */
class Person1(name: String, val age: Int) {
  // We can define instance fields inside the class body also
  val ageAfterTenYears: Int = age + 10

  def getName: String = this.name // name becomes a private field

  def printPerson(): Unit = println(s"Person name: ${this.name} and age: ${this.age}")

  // this is required to differentiate fields from method parameters with same name
  def greet(name: String): Unit = println(s"Hi, $name! I'm ${this.name}")

  // Method Overloading - same function with different parameters
  def greet(): Unit = println(s"Hi, I'm $name")

  // Constructor Overloading - Can be done using this.
  // The only implementation it can have is to call another constructor
  // This requirement can easily fulfilled by passing default parameters
  def this(name: String) = this(name, 18)
  def this() = this("Alok Senapati")
}
