package com.learning.basics

object ForLoops extends App{
  for (i <- 1 to 10) println(i) // 1 to 10 inclusive 10
  for (i <- 1 until 10) println(i) // 1 to 10 exclusive 10
  for (i <- 1 to 10 if i % 2 == 0) println(i) // With Filter
  private val seq = for (i <- 2 to 20 if i % 2 == 0) yield i // Results in a Collection
  println(seq)
}
