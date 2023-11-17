package com.learning.oop

import scala.annotation.tailrec

abstract class ImmutableLinkedList {
  def head: Int
  def tail: ImmutableLinkedList
  def isEmpty: Boolean
  def add(element: Int): ImmutableLinkedList
  protected def elementToString: String
  override def toString: String = s"[ $elementToString ]"
}

object EmptyLinkedList extends ImmutableLinkedList {
  override def head: Int = throw new Exception("No head found for an EmptyLinkedList")

  override def tail: ImmutableLinkedList = throw new Exception("No tail found for an EmptyLinkedList")

  override def isEmpty: Boolean = true

  override def add(element: Int): ImmutableLinkedList = new Cons(element, EmptyLinkedList)

  override def elementToString: String = ""
}

class Cons(h: Int, t: ImmutableLinkedList) extends ImmutableLinkedList {
  override def head: Int = h

  override def tail: ImmutableLinkedList = t

  override def isEmpty: Boolean = false

  override def add(element: Int): ImmutableLinkedList = new Cons(element, this)

  @tailrec
  private def elementsToString(h: Int, t: ImmutableLinkedList, str: String): String = {
    if t.isEmpty then s"$h $str"
    else elementsToString(t.head, t.tail, s"$h $str")
  }

  override def elementToString: String = elementsToString(this.h, this.t, "").strip()
}

object LinkedListTest extends App {
  System.setProperty("scala.time", "true")
  private val linkedList: ImmutableLinkedList = EmptyLinkedList
  println(linkedList)
  println(linkedList.add(1))
  println(linkedList.add(1).add(2))
  println(linkedList.add(1).add(3).add(4).add(2))
}
