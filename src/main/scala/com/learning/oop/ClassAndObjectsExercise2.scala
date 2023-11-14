package com.learning.oop

object ClassAndObjectsExercise2 extends App {
  val counter = new Counter(1)
  var incrementedCounter = counter.increment(10)
  println(incrementedCounter.currentCount)
  incrementedCounter = counter.increment()
  println(incrementedCounter.currentCount)

  var decrementedCounter = counter.decrement(10)
  println(decrementedCounter.currentCount)
  decrementedCounter = counter.decrement()
  println(decrementedCounter.currentCount)
}

class Counter (count: Int) {
  def currentCount: Int = count

  def increment(step: Int): Counter = new Counter(count + step) // Following Immutability Principal of Functional Programming
  def increment(): Counter = increment(1)

  def decrement(step: Int): Counter = new Counter(count - step)
  def decrement(): Counter = decrement(1)
}
