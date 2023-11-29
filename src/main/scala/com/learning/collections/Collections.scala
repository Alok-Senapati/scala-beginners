package com.learning.collections

object Collections extends App {
  /*
    - 2 Types
    1. Immutable Collections -
      - Immutable collections, never change. We have still operations that simulate additions, removals, or updates,
      - but those operations will in each case return a new collection and leave the old collection unchanged.
      - package - scala.collections.immutable
      - Scala uses immutable collections by default
      - Immutable Collections Hierarchy in Scala
                                Traversable
                                    |
                       --------- Iterable ----------
                       |            |              |
                   -- Set --       Seq         -- Map --
                   |       |        |          |       |
                HashSet SortedSet   |       HashMap SortedMap
                                    |
                          --------------------
                          |                  |
                     IndexedSeq         LinearSeq - | - List
                   /      |     \                   | - Stream
              Vector   Range  String                | - Stack
                                                    | - Queue


      2. Mutable Collections -
        - A mutable collection can be updated, reduced or extended in place.
        - This means you can change, add, or remove elements of a collection as a side effect.
        - package - scala.collections.mutable
        - Mutable Collections Hierarchy in Scala
                                  Traversable
                                      |
                    -------------- Iterable --------------
                    |                 |                  |
             ---- Set ----           Seq           ---- Map ----
            |            |            |           |            |
        HashSet   LinkedHashSet       |       HashMap       MultiMap
                                      |
                      -------------------------------
                      |               |              |
                 IndexedSeq        Buffer         LinearSeq - | - LinkedList
                /          \      /      \                    | - MutableList
        StringBuilder    ArrayBuffer  ListBuffer

    - Traversable -> Base trait for all collections. Offers a great variety of methods:
      - maps -> map, flatMap, collect
      - conversions -> toArray, toList, toSeq
      - size info -> isEmpty, size, nonEmpty
      - tests -> exists, forall
      - folds -> foldLeft, foldRight, reduceLeft, reduceRight
      - retrieval -> head, tail, find
      - string ops -> mkString
    - More collections and hierarchy reference - https://docs.scala-lang.org/overviews/collections-2.13/overview.html
   */
}
