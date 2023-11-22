package com.learning.oop

import scala.runtime.Nothing$

object Generics extends App {
  // Scala Generics helps in implementing same functionality for multiple Datatype
  // Also called type parameterization
  // Classes and Traits can be Type Parameterized
  class MyList[A]

  val intList = new MyList[Int]
  val stringList = new MyList[String]

  // Generic Methods
  object GenericMethods {
    def printSomething[A](data: A) = println(s"$data -> ${data.getClass}")
  }

  GenericMethods.printSomething(20)
  GenericMethods.printSomething("Hello")
  GenericMethods.printSomething(20.0)


  // Variance Problem
  // Let consider we have a class Animal and two subclasses Cat and Dog
  class Animal

  class Cat extends Animal

  class Dog extends Animal

  // If Cat extends Animal, will MyList[Cat] extends MyList[Animal] ?
  // 3 possible answers

  // 1. YES. MyList[ChildClass] extends MyList[SuperClass] => COVARIANCE
  // MyList[+A] allows us to define a class as COVARIANT
  // Object of CovariantList[ChildClass] can be assigned to reference variable of type CovariantList[SuperClass]
  class CovariantList[+A] {
    // This will cause compilation error covariant type A occurs in contravariant position in type A of parameter elem
    // def add(elem: A) = s"An element of type ${elem.getClass} is added"
    def add[B >: A](elem: B) = s"An element of type ${elem.getClass} is added"
  }
  val animalList: CovariantList[Animal] = new CovariantList[Cat]
  // val catsList: CovariantList[Cat] = new CovariantList[Animal] // Will cause compilation error


  // 2. INVARIANCE - MyList[Child/Super Class] can't extends MyList[Super/Child class]
  // [A] allows to define a INVARIANT class
  class InvariantList[A]
  val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal]
  // Below 2 lines will raise compilation error
  // val invariantAnimalsList: InvariantList[Animal] = new InvariantList[Cat]
  // val invariantCatsList: InvariantList[Cat] = new InvariantList[Animal]


  // 3. CONTRAVARIANCE - MyList[SuperClass] extends MyList[ChildClass]
  // [-A] allows to define a CONTRAVARIANT class
  class ContraVariantList[-A]
  val contravariantAnimalList: ContraVariantList[Animal] = new ContraVariantList[Animal]
  val contravariantCatsList: ContraVariantList[Cat] = new ContraVariantList[Animal]
  // Below line will raise compilation error
  // val contravariantAnimalsList: ContraVariantList[Animal] = new ContraVariantList[Cat]

  // But above contravariance relation does not make sense as a ContraVariantList[Animal] can also contain Dog
  // So assigning it to a reference variable of type ContraVariantList[Cat] is incorrect.
  // But Below is a example of where Contravariance can be useful.
  // A Trainer of an Animal can also train Cats
  class Trainer[-A]
  val contravariantCatsTrainer: Trainer[Cat] = new Trainer[Animal]


  // Bounded Types
  // UpperBounded - A Generic class can only contains a child class types of another class
  class Cage[A <: Animal]
  var animalCage: Cage[Animal] = new Cage[Animal]

  class Car
  // val carCage = new Cage[Car] // Will result in compilation error

  // LowerBounded - A Generic class can only contains a parent class types of another class
  class Parent1
  class Child1 extends Parent1
  class Child2 extends Child1
  class Child3 extends Parent1
  class LowerBoundedGeneric[A >: Child2]
  val child2 = new LowerBoundedGeneric[Child2]
  var child1 = new LowerBoundedGeneric[Child1]
  var parent1 = new LowerBoundedGeneric[Parent1]


  class Parent2
  // val parent2 = new LowerBoundedGeneric[Parent2] // Will result in compilation error


  // If I add a Dog to a covariantList of Animal, which refers to a CovariantList of Cats, will it be possible ???
  val animalCovariantList: CovariantList[Animal] = new CovariantList[Cat]  // animalCovariantList refers to a CovariantList of Cat
  // animalCovariantList.add(new Dog)  // It is logically incorrect if I add a Dog to a CovariantList of Cats
  // But if the add method returns a CovariantList[Animal] instead of CovariantList[Cat] then it's logically correct.
  // As a CovariantList[Animal] can also contain a Dog
  println(animalCovariantList.add(new Dog))

}
