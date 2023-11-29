package com.learning.collections

object TuplesAndMaps extends App {
  /*
    Tuples -
      - Fine Ordered Collection similar to List
      - TupleX can contain X elements of different types. X can be 1 to 22
   */
  val aTuple2: Tuple2[Int, Int] = (1, 2) // Can also be written as 1 -> 2. This notation only works for Tuple2
  val aTuple3: Tuple3[Int, String, Double] = (10, "Alok", 2.0)

  // ._X denotes the element at Xth place starting from 1
  println(aTuple2._1)
  println(aTuple3._2)
  println(aTuple3._3)

  // Tuple2 has a swap method which returns a new tuple with swapped elements
  println(aTuple2.swap)

  // copy
  val aCopiedTuple = aTuple2.copy()
  val aCopiedModifiedTuple = aTuple3.copy(_3 = 20.0)
  println(aCopiedTuple)
  println(aCopiedModifiedTuple)


  /*
    Maps -
      - Collection of Key, Value Pairs
   */
  var aMap: Map[String, Int] = Map()
  aMap = aMap + ("Alok" -> 10)
  println(aMap)

  // Accessing elements
  val phoneBook: Map[String, Int] = Map(("Alok", 86879897), "John" -> 9789687, "Mary" -> 64545546).withDefaultValue(-1)
  // withDefaultValue is used to return a default value for a key that is not present
  println(phoneBook("Alok"))
  println(phoneBook("Harry"))  // Will throw RunTimeException: key not found is withDefaultValue is not applies
  println(phoneBook.contains("Harry"))  // Check if a key is present
  println(phoneBook.getOrElse(key="Harry", default=99999))  // Returns a default value if the key is not present. Does not throw Exception even if .withDefaultValue is not applied

  // phoneBook("Alok") = 97986876  // Throws Compile Time Exception as Map is immutable
  val aNewPhonebook = phoneBook.updated("Alok", 98778656)  // Returns a new Map with updated value
  println(aNewPhonebook)

  val aNewPhonebookKNF = phoneBook.updated("Harry", 76586586)  // Adds a (K, V) pair if the key is not present
  println(aNewPhonebookKNF)
  // We can also add/update using + operator as shown above for aMap. It calls updated function internally

  // Remove a key
  println(phoneBook.removed("Alok"))

  // Concatenating 2 Maps
  println(phoneBook)
  println(phoneBook ++ Map("Alok" -> 142321313, "Harry" -> 876768545))

  // map
  println(phoneBook.map(pair => pair._1.toLowerCase() -> pair._2))

  // filter
  println(phoneBook.filter(pair => pair._1.startsWith("A")))

  // Conversions
  println(phoneBook.toList) // Returns List of Pairs or Tuple2
  println(List("A" -> 10, "B" -> 20).toMap)  // List[Tuple2] to Map
  println(List("Alok", "Akshay", "John", "Jerry", "Bob", "Kevin").groupBy(item => item(0)))  // groupBy returns a HashMap where values are Lists
}
