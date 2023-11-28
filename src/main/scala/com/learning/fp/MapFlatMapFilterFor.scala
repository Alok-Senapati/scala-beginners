package com.learning.fp

import com.learning.exercises.GenericCovariantList.{ConsGenericList, EmptyGenericList}

object MapFlatMapFilterFor extends App {
  val list: List[Int] = List(1, 2, 3)

  // map
  println(list.map(x => x * 2))

  // filter
  println(list.map(x => x % 2 != 0))

  // flatMap
  println(list.flatMap(x => List(x, x * 2, x * 3)))

  // Print All Combinations from 3 Lists
  val nums: List[Int] = List(1, 4, 3, 2, 9)
  val strings: List[String] = List("Hello", "Scala")
  val colors: List[String] = List("Red", "Blue", "Green")
  println(nums.filter(_ % 2 == 0).flatMap(x => strings.flatMap(y => colors.map(c => s"$x - $y - $c"))))

  // foreach - applies a function which has Unit as return type
  list.foreach(println)

  // For Comprehension
  val combinations = for {
    n <- nums if n % 2 == 0
    s <- strings
    c <- colors
  } yield s"$n - $s - $c"
  println(combinations)

  // For Comprehension for foreach
  for {
    l <- list
  } println(l)

  // Syntax Overload
  println(nums.map { x =>
    x * 2
  })

  /*  Exercise:
      1.  GenericCovariantList supports for comprehensions?
          map(f: A => B) => GenericCovariantList[B]
          filter(p: A => Boolean) => GenericCovariantList[A]
          flatMap(f: A => MyList[B]) => GenericCovariantList[B]
      2.  A small collection of at most ONE element - Maybe[+T]
          - map, flatMap, filter
     */

  /* 1. For Comprehension can be applied on GenericCovariantList[+A] the class have below methods implemented
      map(f: A => B) => GenericCovariantList[B]
      withFilter(p: A => Boolean) => GenericCovariantList[A]
      flatMap(f: A => MyList[B]) => GenericCovariantList[B]
   */
  val consList = ConsGenericList[Int](1, ConsGenericList(2, ConsGenericList(3, ConsGenericList(4, ConsGenericList(5, EmptyGenericList)))))
  for {
    x <- consList
  } println(x)
  println(for {
    x <- consList if x % 2 != 0
  } yield x)
  
  // 2. Check com.learning.exercises.MayBe
}
