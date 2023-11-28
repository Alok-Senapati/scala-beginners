package com.learning.collections

object Collections extends App {
  /*
    - 2 Types
    1. Immutable Collections -
      - Collections that never change. Every time a new collection is instantiated for any change.
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
                  /      |      \                   | - Stream
              Vector   Range  String                | - Stack
                                                    | - Queue


      2. Mutable Collections -
        - Collections that can change in place.
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
               /           \      /      \                    | - MutableList
        StringBuilder    ArrayBuffer  ListBuffer
   */

}
