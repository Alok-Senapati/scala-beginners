package com.learning.exercises

import org.graalvm.compiler.asm.amd64.AMD64VectorAssembler.VexFloatCompareOp.Predicate

import scala.annotation.{tailrec, targetName}

object GenericCovariantList extends App {
  abstract class GenericList[+A] {
    def head: A
    def tail: GenericList[A]
    def isEmpty: Boolean
    def add[B >: A](element: B): GenericList[B]
    def elementToString: String
    override def toString: String = s"[ $elementToString ]"
    def map[B](transformer: A => B): GenericList[B]
    def flatMap[B](transformer: A => GenericList[B]): GenericList[B]
    def filter(predicate: A => Boolean): GenericList[A]
    def withFilter(predicate: A => Boolean): GenericList[A] = filter(predicate)
    @targetName("concat")
    def ++[B >: A](list: GenericList[B]): GenericList[B]
    def foreach(f: A => Unit): Unit
    def sort(compare: (A, A) => Int): GenericList[A]
    def zipWith[B, C](list: GenericList[B], f: (A, B) => C): GenericList[C]
    def fold[B](start: B)(f: (B, A) => B): B
    def reverse: GenericList[A] = {
      @tailrec
      def doReverse(list: GenericList[A], rev: GenericList[A]): GenericList[A] = {
        if list.isEmpty then rev
        else doReverse(list.tail, rev.add(list.head))
      }
      doReverse(this, EmptyGenericList)
    }
  }

  // Nothing is a subtype of all other types in Scala
  case object EmptyGenericList extends GenericList[Nothing] {
    override def head: Nothing = throw new Exception("Empty List has no head")

    override def tail: GenericList[Nothing] = throw new Exception("Empty List has no tail")

    override def isEmpty: Boolean = true

    override def add[B >: Nothing](element: B): GenericList[B] = ConsGenericList(element, EmptyGenericList)

    override def elementToString: String = ""

    override def map[B](transformer: Nothing => B): GenericList[B] = EmptyGenericList

    override def flatMap[B](transformer: Nothing => GenericList[B]): GenericList[B] = EmptyGenericList

    override def filter(predicate: Nothing => Boolean): GenericList[Nothing] = EmptyGenericList

    @targetName("concat")
    override def ++[B >: Nothing](list: GenericList[B]): GenericList[B] = list

    override def foreach(f: Nothing => Unit): Unit = ()

    override def sort(compare: (Nothing, Nothing) => Int): GenericList[Nothing] = EmptyGenericList

    override def zipWith[B, C](list: GenericList[B], f: (Nothing, B) => C): GenericList[C] = {
      if !list.isEmpty then throw new RuntimeException("Lists have different length")
      else EmptyGenericList
    }

    override def fold[B](start: B)(f: (B, Nothing) => B): B = start
  }

  case class ConsGenericList[+A](h: A, t: GenericList[A]) extends GenericList[A] {
    override def head: A = h

    override def tail: GenericList[A] = this.t

    override def isEmpty: Boolean = false

    override def add[B >: A](element: B): GenericList[B] = new ConsGenericList[B](element, this)

    override def elementToString: String = {
      if t.isEmpty then "" + h
      else "" + h + ", " + t.elementToString
    }

    override def filter(predicate: A => Boolean): GenericList[A] = {
      if predicate(h) then ConsGenericList(h, t.filter(predicate))
      else t.filter(predicate)
    }

    override def map[B](transformer: A => B): GenericList[B] = {
      new ConsGenericList[B](transformer(h), t.map(transformer))
    }

    @targetName("concat")
    override def ++[B >: A](list: GenericList[B]): GenericList[B] = {
      new ConsGenericList[B](h, t ++ list)
    }

    override def flatMap[B](transformer: A => GenericList[B]): GenericList[B] = {
      transformer(h) ++ t.flatMap(transformer)
    }

    override def foreach(f: A => Unit): Unit = {
      f(h)
      t.foreach(f)
    }

    override def sort(compare: (A, A) => Int): GenericList[A] = {
      def insert(x: A, sortedList: GenericList[A]): GenericList[A] = {
        if sortedList.isEmpty then ConsGenericList(x, EmptyGenericList)
        else if compare(x, sortedList.head) <= 0 then ConsGenericList(x, sortedList)
        else ConsGenericList(sortedList.head, insert(x, sortedList.tail))
      }

      @tailrec
      def sortHelper(unsorted: GenericList[A], sorted: GenericList[A]): GenericList[A] ={
        if unsorted.isEmpty then sorted
        else sortHelper(unsorted.tail, insert(unsorted.head, sorted))
      }

      sortHelper(this, EmptyGenericList)
    }

    override def zipWith[B, C](list: GenericList[B], f: (A, B) => C): GenericList[C] = {
      @tailrec
      def zipHelper(list1: GenericList[A], list2: GenericList[B], res: GenericList[C]): GenericList[C] = {
        if list1.isEmpty && list2.isEmpty then res
        else if list2.isEmpty then throw new RuntimeException("Lists have different lengths")
        else zipHelper(list1.tail, list2.tail, res.add(f(list1.head, list2.head)))
      }
      zipHelper(this, list, EmptyGenericList).reverse
    }

    override def fold[B](start: B)(f: (B, A) => B): B = {
      @tailrec
      def foldHelper(list: GenericList[A], res: B): B = {
        if list.isEmpty then res
        else foldHelper(list.tail, f(res, list.head))
      }
      foldHelper(this, start)
    }
  }


  /*
    Exercises:
    1.  Generic trait MyPredicate[-T] with a little method test(T) => Boolean
    2.  Generic trait MyTransformer[-A, B] with a method transform(A) => B
    3.  MyList:
        - map(transformer) => MyList
        - filter(predicate) => MyList
        - flatMap(transformer from A to MyList[B]) => MyList[B]

        class EvenPredicate extends MyPredicate[Int]
        class StringToIntTransformer extends MyTransformer[String, Int]

        [1,2,3].map(n * 2) = [2,4,6],
        [1,2,3,4].filter(n % 2) = [2,4]
        [1,2,3].flatMap(n => [n, n+1]) => [1,2,2,3,3,4]
   */

  private var intList: GenericList[Int] = EmptyGenericList
  private var stringList: GenericList[String] = EmptyGenericList

  intList = new ConsGenericList[Int](1, new ConsGenericList[Int](3, new ConsGenericList[Int](4, EmptyGenericList)))
  stringList = new ConsGenericList[String]("Hello", new ConsGenericList[String]("World", EmptyGenericList))

  println(intList)
  println(stringList)

  val aConsGenericList: ConsGenericList[Int] = ConsGenericList[Int](1, ConsGenericList[Int](3, ConsGenericList[Int](6, ConsGenericList[Int](9, ConsGenericList[Int](22, EmptyGenericList)))))
  println(aConsGenericList)
  println(aConsGenericList.filter((item: Int) => item % 2 == 0))
  println(aConsGenericList.map((item: Int) => item * item))
  println(aConsGenericList.flatMap((item: Int) => ConsGenericList(item, ConsGenericList(item * 2, EmptyGenericList))))
}
