package com.learning.oop

object TraitsDiamondProblem extends App {

  /*
    - If my class extends 2 traits, both having a method with same signature and does not extends any common parent
    - Then the compiler with throw Error due to conflicting methods
   */
  trait Vehicle {
    def start(): Unit = println("Vehicle is started.")
  }

  trait Wheeler4 {
    def start(): Unit = println("4 wheeler is started.")
  }

  // class Car extends Vehicle with Wheeler4  // Will raise error


  /*
    - If my class extends 2 traits, both extending same Parent class
    - Parent class has a method defined/implemented
    - Both the child class overrides the methods with their respective implementation without calling method from super
    - If I call the method from instance of my class then It will call the functions from the last trait that comes in extends/with statement
    */
  trait Human {
    def aboutMe: String = "I am a Human"
  }

  trait Man extends Human {
    override def aboutMe: String = "I am a Man"
  }

  trait Old extends Human {
    override def aboutMe: String = "I am Old"
  }

  class OldMan extends Old with Man
  class ManOld extends Man with Old

  println((new OldMan).aboutMe)
  println((new ManOld).aboutMe)


  /*
    - If my class extends 2 traits, both extending same Parent class
    - Parent class has a method defined/implemented
    - Both the child class overrides the methods with their respective implementation with calling method from super
    - If I call the method from instance of my class then It will call the functions from the last trait -> first trait methods and then super class method
    */


  trait Animal {
    def iAm: String = "I am a Animal"
  }

  trait Wild extends Animal {
    override def iAm: String = {
      println("I am a Wild Animal")
      super.iAm
    }
  }

  trait Carnivorous extends Animal {
    override def iAm: String = {
      println("I am a carnivorous Animal")
      super.iAm
    }
  }

  trait BigAnimal extends Animal {
    override def iAm: String = {
      println("I am a big Animal")
      super.iAm
    }
  }

  class WildCarnivorous extends Wild with Carnivorous
  class CarnivorousWild extends Carnivorous with Wild

  class WildBigCarnivorous extends Wild with Carnivorous with BigAnimal

  println((new WildCarnivorous).iAm)
  println((new CarnivorousWild).iAm)
  println((new WildBigCarnivorous).iAm)
}
