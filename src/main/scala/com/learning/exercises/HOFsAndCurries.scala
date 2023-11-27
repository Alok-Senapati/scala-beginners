package com.learning.exercises

import com.learning.exercises.GenericCovariantList.{ConsGenericList, EmptyGenericList}

object HOFsAndCurries extends App {
  /*
      1.  Expand GenericCompanionList
          - foreach method A => Unit
            [1,2,3].foreach(x => println(x))

          - sort function ((A, A) => Int) => MyList
            [1,2,3].sort((x, y) => y - x) => [3,2,1]

          - zipWith (list, (A, A) => B) => MyList[B]
            [1,2,3].zipWith([4,5,6], x * y) => [1 * 4, 2 * 5, 3 * 6] = [4,10,18]

          - fold(start)(function) => a value
            [1,2,3].fold(0)(x + y) = 6

      2.  toCurry(f: (Int, Int) => Int) => (Int => Int => Int)
          fromCurry(f: (Int => Int => Int)) => (Int, Int) => Int

      3.  compose(f,g) => x => f(g(x))
          andThen(f,g) => x => g(f(x))
     */
  val aConsGenericList: ConsGenericList[Int] = ConsGenericList[Int](1, ConsGenericList[Int](3, ConsGenericList[Int](6, ConsGenericList[Int](9, ConsGenericList[Int](22, EmptyGenericList)))))
  private val aConsGenericListNew: ConsGenericList[Int] = ConsGenericList[Int](2, ConsGenericList[Int](1, ConsGenericList[Int](4, ConsGenericList[Int](3, ConsGenericList[Int](2, EmptyGenericList)))))
  aConsGenericList.foreach(println)
  println(aConsGenericList.sort((x, y) => y - x))
  println(aConsGenericList)
  println(aConsGenericListNew)
  println(aConsGenericList.zipWith(aConsGenericListNew, (x: Int, y: Int) => x * y))
  println(aConsGenericList.fold(10)(_ + _))

  // 2. Solution:
  def toCurry(f: (Int, Int) => Int): Int => Int => Int = (x: Int) => (y: Int) => f(x, y)

  val curriedFunction = toCurry((x: Int, y: Int) => x * y)
  println(curriedFunction(2)(3))

  def fromCurry(f: Int => Int => Int): (Int, Int) => Int = (x: Int, y: Int) => f(x)(y)

  val uncurriedFunction = fromCurry(curriedFunction)
  println(uncurriedFunction(2, 3))

  // 3. Solution:
  def compose[T, A, B](f: A => B, g: T => A): T => B = (x: T) => f(g(x))
  def andThen[T, A, B](f: T => A, g: A => B): T => B = (x: T) => g(f(x))

  println(compose[Int, String, Int](_.toInt, _ + "0")(2))
  println(andThen[String, Int, Int](_.toInt * 100, _ + 200)("65"))

  val func1: Int => Int = _ * 3
  val func2: Int => Int = _ + 10
  println(compose(func1, func2)(2))
  println(andThen(func1, func2)(2))

}

