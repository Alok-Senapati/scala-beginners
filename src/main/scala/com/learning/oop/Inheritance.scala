package com.learning.oop

object Inheritance extends App {
  class Animal {
    val creatureType = "wild"
    def eat = println("nomnom")
  }

  class Cat extends Animal {
    def crunch = {
      eat
      println("crunch crunch")
    }
  }

  val cat = new Cat
  cat.crunch

  class Person(name: String, age: Int = 0)
  // Child class constructor must call Parent class constructor
  // class Adult extends Person  // Raise compilation error
  class Adult(name: String, age: Int, idCard: String) extends Person(name, age)
  class NewBorn(name: String, age: Int, months: Int) extends Person(name)


  // Method overriding
  // super -> parent class reference
  class Dog extends Animal {
    // attribute/field overriding
    override val creatureType: String = "domestic"
    override def eat = {
      super.eat
      println("crunch crunch")
    }
  }
  val dog = new Dog
  dog.eat
  println(dog.creatureType)

  // Field overriding in constructor
  class Monkey(override val creatureType: String) extends Animal {
    override def eat = println("yum yum")
    def jump: String = "Woohh!!"
  }

  val monkey = new Monkey("wild")
  monkey.eat
  println(monkey.creatureType)

  val anAnimal: Animal = new Monkey("domestic")
  anAnimal.eat

  // A reference variable of Parent type can only use overridden methods/fields of child class instance
  // anAnimal.jump // Raise an exception

  val aMonkey = new Monkey("wild")
  println(aMonkey.jump)

  // final keyword with a method restricts child class to override it
  // final keyword with class restricts any class to inherit it
}
