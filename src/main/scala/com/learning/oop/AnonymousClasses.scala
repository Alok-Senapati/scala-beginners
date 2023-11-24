package com.learning.oop

object AnonymousClasses extends App {
  abstract class Animal {
    def eat: String
  }

  // Even though Animal is an abstract class we can instantiate a reference to an anonymous class that extends Animal
  val someAnimal: Animal = new Animal {
    override def eat: String = "YumYumYum"
  }
  println(someAnimal.getClass)


  /*
  The above code is equivalent/compiled as:
  class $anon$1 extends Animal {
    override def eat: String = "YumYumYum"
  }
  val someAnimal1: Animal = new $anon$1
  println(someAnimal1.getClass)
  */

  // We can create Anonymous classes for class(abstract/non-abstract and traits)
  // Anonymous classes for abstract class/traits must implement the abstract attributes and methods

  class Person(name: String, age: Int) {
    def sayHi = println(s"Hi!! I'm $name")
  }
  
  // An Anonymous class for a class having a constructor must pass values to the constructor parameters
  val aPerson: Person = new Person(name="Jack", age=18) {
    override def sayHi: Unit = {
      super.sayHi
      println(s"It's an anonymous function: ${this.getClass}")
    }
  }

  aPerson.sayHi
}
