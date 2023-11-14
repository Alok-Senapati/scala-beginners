package com.learning.oop

object ClassAndObjectsExercise1 extends App {
  val author = new Writer("Alok", "Senapati", 1999)
  val imposter = new Writer("Alok", "Senapati", 1999)
  println(author.fullName)
  val novel = new Novel("Some Novel", 2023, author)
  println(novel.isWrittenBy(author))
  println(novel.isWrittenBy(imposter))
  println(novel.authorAge)
  val newNovel = novel.copy(2025)
  println(newNovel.yearOfRelease)
}

class Writer (firstName: String, surname: String, val yearOfBirth: Int) {
  def fullName: String = this.firstName + " " + this.surname
}

class Novel (val name: String, val yearOfRelease: Int, author: Writer) {
  def authorAge: Int = yearOfRelease - author.yearOfBirth
  def isWrittenBy(author: Writer): Boolean = author == this.author // Compares the reference
  def copy(newYear: Int): Novel = new Novel(name, newYear, author)
}