package com.learning.oop

object CaseClasses extends App {
  // case classes provides some more advantages over classes and extremely helpful for scenario's like
  // implementing collections and data structures, distributed computing, pattern matching etc.

  // 1. Class parameters are also class attributes/fields without any val keyword in constructor
  case class Person(name: String, age: Int)

  val aPerson = Person("Alok", 25)
  println(aPerson.name)
  println(aPerson.age)

  // 2. Sensible toString Implementation - Unlike classes, case classes provide a more readable toString Implementation
  println(aPerson)

  // 3. equals and hashCode already implemented out of the box.
  // Unlike classes, equals returns true comparing the attributes/fields and not comparing the reference
  val jack = Person("Jack", 22)
  val jackCopy = Person("Jack", 22)
  println(jack == jackCopy)

  // 4. Have handy copy methods
  val aPersonCopy = aPerson.copy()
  val aPersonCopyWithChangedAge = aPerson.copy(age=24)
  println(aPersonCopy)
  println(aPersonCopyWithChangedAge)

  // 5. Case classes already have companion objects and apply method works as a constructor
  val aPersonCompanionObject = Person
  val aPersonWithConstructor = Person("Name", 18)
  val aPersonWithApplyFunction = aPersonCompanionObject.apply("Name", 18) // same as Person.apply("Name", 18)
  println(aPersonWithConstructor)
  println(aPersonWithApplyFunction)
  println(aPersonWithConstructor == aPersonWithApplyFunction)

  // 6. Case classes are serializable, which helps in distributed systems - Akka and Spark Frameworks

  // 7. Case classes have extractor patterns i.e. They can be used for Pattern Matching

  // 8. Case Objects - Same as CC, but they don't get their companion object as they are their own companion object
  // They don't take constructor parameters
  case object UnitedKingdom {
    def name: String = "United Kingdom"
  }
  println(UnitedKingdom.name)
  
  
  // Exercise - Expand GenericList using case classes and case objects where ever applicable
}
