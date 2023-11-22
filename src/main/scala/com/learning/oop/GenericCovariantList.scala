package com.learning.oop

import scala.annotation.tailrec

object GenericCovariantList extends App {
  abstract class GenericList[+A] {
    def head: A
    def tail: GenericList[A]
    def isEmpty: Boolean
    def add[B >: A](element: B): GenericList[B]
    def elementToString: String
    override def toString: String = s"[ $elementToString ]"
  }

  // Nothing is a subtype of all other types in Scala
  object EmptyGenericList extends GenericList[Nothing] {
    override def head: Nothing = throw new Exception("Empty List has no head")

    override def tail: GenericList[Nothing] = throw new Exception("Empty List has no tail")

    override def isEmpty: Boolean = true

    override def add[B >: Nothing](element: B): GenericList[B] = new ConsGenericList(element, EmptyGenericList)

    override def elementToString: String = ""
  }

  class ConsGenericList[+A](h: A, t: GenericList[A]) extends GenericList[A] {
    override def head: A = h

    override def tail: GenericList[A] = this.t

    override def isEmpty: Boolean = false

    override def add[B >: A](element: B): GenericList[B] = new ConsGenericList[B](element, this)

    override def elementToString: String = {
      if t.isEmpty then "" + h
      else "" + h + ", " + t.elementToString
    }
  }

  var intList: GenericList[Int] = EmptyGenericList
  var stringList: GenericList[String] = EmptyGenericList

  intList = new ConsGenericList[Int](1, new ConsGenericList[Int](3, new ConsGenericList[Int](4, EmptyGenericList)))
  stringList = new ConsGenericList[String]("Hello", new ConsGenericList[String]("World", EmptyGenericList))

  println(intList)
  println(stringList)
}
