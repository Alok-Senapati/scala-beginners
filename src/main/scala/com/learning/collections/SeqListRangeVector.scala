package com.learning.collections

import scala.util.Random

object SeqListRangeVector extends App {
  /*
  Sequences -
    A general interface for data structures that
      - have a well defined order
      - can be indexed
    Supports various operations
      - apply, iterator, length, reverse for indexing and iterating
      - concatenation, prepending, appending
      - a lot of other functions: grouping, sorting, zipping, searching, slicing
   */

  // Delegates to a child List or other child classes based on the value assigned, It can utilize the all functions that are present in Seq
  // If any method is override in List, then it will use the List Implemented Function
  // But won't be able to use methods that are only there in List
  val aSeq: Seq[Int] = Seq(1, 2, 4, 3, 7, 5)
  println(aSeq)
  println(aSeq.reverse)
  println(aSeq.isEmpty)
  println(aSeq ++ Seq(4, 3, 5)) // Concatenation
  println(9 +: aSeq) // prepend
  println(aSeq :+ 7) // append
  println(aSeq(2)) // Indexing
  println(aSeq.sorted)
  println(aSeq.slice(1, 4)) // slicing

  // Partitioning - Splits the Seq into 2 Seq based on the predicate
  val (even, odd) = aSeq.partition(p => p % 2 == 0)
  println(even)
  println(odd)

  val head :: tail = aSeq  // Separates head and tail
  println(head)
  println(tail)

  println(aSeq.groupBy(p => p % 2))  // Grouping based on a function

  /*
  Ranges -
    Provides an iterable of a range
   */
  val aRangeInclusive = 1 to 10 // inclusive 10
  val aRangeExclusive = 1 until 10  // exclusive 10
  println(aRangeExclusive)
  println(aRangeInclusive)

  println(aRangeExclusive.mkString("Range(", ", ", ")"))
  println(aRangeInclusive.mkString("Range(", ", ", ")"))
  println(aRangeInclusive.foldLeft("")((x, y) => s"$x$y"))

  /*
  List -
    - A LinearSeq immutable linked list
      - head, tail, isEmpty is fast O(1)
      - most operations are O(n): length, reverse
      - It's a sealed class with 2 child class/object
        - Nil - Empty List
        - ::
   */
  val aList = ::(10, ::(20, Nil))  // Same as List(10, 20)
  println(aList)

  val newList = List(10, 3, 9, 6, 2)
  println(2 :: newList) // prepend
  println(2 +: newList) // prepend

  println(newList :+ 5) // append
  // println(newList :: 5) // will raise compilation error

  // fill
  val filledList = List.fill(5)(20)  // 5 -> length of the list, 20 -> value to be filled
  println(filledList)
  val updatedList = filledList.updated(1, 3)  // As list is immutable direct assignment to index won't work, instead updated method can be used
  println(updatedList)
  println(updatedList(3)) // Accessing elements using index

  /*
    Arrays -
      - The equivalent of simple Java Arrays
      - Can be manually constructed with predefined length
      - Can be mutated in place
      - Interoperable with Java's Arrays
      - Indexing is fast
   */
  val anArray = Array(1, 4, 3, 2, 5)
  println(anArray)  // Array does not have toString implemented
  def printArray[T]: Array[T] => Unit = (arr: Array[T]) => println(arr.mkString("Array(", ", ", ")"))
  printArray(anArray)
  anArray(2) = 9  // In place update using index
  printArray(anArray)

  val threeArray = Array.ofDim[Int](3)  // Integer Array of length 3 filled with default values
  printArray(threeArray)

  val twoDimArray = Array.ofDim[Int](2, 4)  // 2 * 4 Integer Metrics
  twoDimArray.foreach(arr => printArray(arr))
  twoDimArray(1)(2) = 4  // In place update of 2D Array

  val anArraySeq: Seq[Int] = anArray // Implicit Conversion
  println(anArraySeq)


  /*
    Vector -
    - The default implementation for immutable sequences
    - Effectively constant index read and write O(log32(n))
    - Fast element addition: append/prepend
    - Implemented on a fixed branch trie. Branch Factor 32
    - Good Performance for Lange Sizes
   */

  // Performance Test - List vs Vector
  val maxLength = 100000
  val maxIterations = 1000
  def getWriteTime(collection: Seq[Int]): Double = {
    val runTimes: Seq[Double] = for {
      i <- 1 to maxIterations
    } yield {
      val startTime = System.nanoTime()
      collection.updated(Random.nextInt(maxLength), Random.nextInt(1000))
      val endTime = System.nanoTime()
      endTime - startTime
    }

    runTimes.sum / maxIterations
  }

  // advantage - Keeps track of the tail
  // disadvantage - updating an element in middle is expansive
  println(getWriteTime(List.fill[Int](maxLength)(0)))

  // advantages - as Vector uses HAMT (Hashed Array Mapped Trie). Depth of the tree is less
  // disadvantage - needs to replace an entire 32-element chunk
  println(getWriteTime(Vector.fill[Int](maxLength)(0)))

}
