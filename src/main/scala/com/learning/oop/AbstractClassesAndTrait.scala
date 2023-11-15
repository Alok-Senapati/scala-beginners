package com.learning.oop

object AbstractClassesAndTrait extends App {
  abstract class Animal {
    val creatureType: String // Abstract Field
    def eat: String // Abstract Method
  }

  // Abstract classes cannot be instanced. They can only be inherited
  // Any classes inheriting an abstract class must implement the abstract fields and attributes or else declare the class as abstract
  class Dog(override val creatureType: String) extends Animal {
    override def eat: String = "crunch crunch"  // override keyword in optional for abstract fields or methods
  }

  class Deer(override val creatureType: String = "Deer") extends Animal {
    override def eat: String = "chaw chaw"
  }


  // A trait is like an Interface in Java and similar to abstract class
  // The difference between a train and an abstract class is
  // A class can only inherit only one abstract class but can implement one or more traits


  trait Carnivore {
    def eat(animal: Animal): String
  }

  trait Wild {
    def prey(animal: Animal): String
  }

  // An abstract class can inherit another abstract class and multiple traits
  // A trait can also can inherit another abstract class and multiple traits

  // A class can inherit multiple traits using with keyword
  class Tiger(override val creatureType: String = "Tiger") extends Animal with Carnivore with Wild {
    override def eat: String = "Tiger eats flesh."

    def eat(animal: Animal): String = s"${this.creatureType} is eating ${animal.creatureType}"  // as override is optional for abstract method

    def prey(animal: Animal): String = s"${this.creatureType} hunts ${animal.creatureType}"
  }

  val tiger1: Animal = new Tiger()
  val tiger2: Carnivore = new Tiger()
  val tiger3: Wild = new Tiger()
  val tiger4: Tiger = new Tiger()

  val deer: Animal = new Deer()

  println(tiger1.creatureType)
  println(tiger1.eat)
  // println(tiger1.eat(deer)) // raise Exception as tiger1 is of type Animal so it can only use the overridden eat method from Animal class in child class
  
  // println(tiger2.creatureType) // raise error as Carnivore doesn't have creatureType field
  println(tiger2.eat) // 

}
