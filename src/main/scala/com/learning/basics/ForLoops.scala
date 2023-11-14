package com.learning.basics

object ForLoops extends App{
  for (i <- 1 to 10) println(i) // 1 to 10 inclusive 10
  for (i <- 1 until 10) println(i) // 1 to 10 exclusive 10
  for (i <- 1 to 10 if i % 2 == 0) println(i) // With Filter
  for (i <- 10 to 0 by -2) println(i) // With step
  for (i <- 1 to 5; j <- 5 to 10) println(i + " " + j) // Like Nested Loop
  private val seq = for (i <- 2 to 20 if i % 2 == 0) yield i // Results in a Collection
  println(seq)
}
