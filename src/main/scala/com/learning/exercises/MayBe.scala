package com.learning.exercises

abstract class MayBe[+T] {
  def map[A](f: T => A): MayBe[A]
  def flatMap[A](f: T => MayBe[A]): MayBe[A]
  def filter(f: T => Boolean): MayBe[T]
  def withFilter(f: T => Boolean): MayBe[T] = filter(f)
}

case object MayBeNot extends MayBe[Nothing] {
  override def map[A](f: Nothing => A): MayBe[A] = MayBeNot

  override def flatMap[A](f: Nothing => MayBe[A]): MayBe[A] = MayBeNot

  override def filter(f: Nothing => Boolean): MayBe[Nothing] = MayBeNot
}

case class Just[+T](value: T) extends MayBe[T] {
  override def map[A](f: T => A): MayBe[A] = Just(f(value))

  override def flatMap[A](f: T => MayBe[A]): MayBe[A] = f(value)

  override def filter(f: T => Boolean): MayBe[T] = if f(value) then this else MayBeNot
}

object MayBeTest extends App {
  val mayBeNot: MayBe[Any] = MayBeNot
  println(mayBeNot)
  val mayBe: Just[Int] = Just(10)
  println(mayBe.value)
  println(mayBe.map(_ * 2))
  println(mayBe.flatMap(x => Just(x + 1)))
  println(mayBe.filter(_ % 2 != 0))
}
