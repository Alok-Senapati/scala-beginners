package com.learning.collections

import scala.util.{Failure, Random, Success, Try}

object HandlingFailure extends App {
  /*
    Exceptions are handled with try-catch block.
    Problems with try-catch block
      - Multiple nested try-catch makes the code hard to follow
      - We can't chain multiple operations prone to failure

    Try[T] -
      - Functional way of handling Exceptions
      - Wrapper for a computation that might fail or not
      - Values/Subclasses are
        - Failure[T](exception: Throwable) -> Wrap failed computations
        - Success[T](value: T) -> Wrap succeeded computations
   */

  val aSuccess: Try[Int] = Success(3)
  val aFailure: Try[Int] = Failure(new RuntimeException("This is a Failure"))
  println(aSuccess)
  println(aFailure)

  // Handling methods that might throw exception
  def divide(a: Int, b: Int): Int = a / b

  val potentialFailure = Try(divide(5, 0))
  val potentialSuccess = Try(divide(4, 2))
  println(potentialFailure)
  println(potentialSuccess)

  // Syntactic Sugar
  val anotherPotentialFailure = Try {
    // code that might throw exceptions/failure
    val s: String = null
    s.toUpperCase()
  }
  println(anotherPotentialFailure)

  // Utilities
  println(potentialSuccess.isSuccess)
  println(potentialSuccess.isFailure)
  println(potentialFailure.isSuccess)
  println(potentialFailure.isFailure)

  // orElse
  def unsafeMethod: String = throw new RuntimeException("Testing Failure")
  def backupMethod: String = "SuccessMessage"
  val fallbackTry = Try(unsafeMethod).orElse(Try(backupMethod))

  // The more appropriate implementation will be to use Try as return type for the functions that might cause exceptions
  def betterUnsafeMethod: Try[String] = Failure(new RuntimeException("BetterUnsafeMethod"))
  def betterBackupMethod: Try[String] = Success("BetterBackupMethod")
  val betterFallbackTry = betterUnsafeMethod orElse betterBackupMethod

  // map, flatMap, filter
  println(aSuccess.map(_ * 2))  // Success(6)
  println(aFailure.map(_ * 2))  // Failure(java.lang.RuntimeException: This is a Failure)

  println(aSuccess.flatMap(x => Try(x * 10)))  // Success(30)
  println(aFailure.flatMap(x => Try(x * 10)))  // Failure(java.lang.RuntimeException: This is a Failure)

  println(aSuccess.filter(x => x > 10)) // Returns Failure if the predicate returns false: Failure(java.util.NoSuchElementException: Predicate does not hold for 3)


  /*
      Exercise
     */
  val host = "localhost"
  val port = "8080"

  private def renderHTML(page: String): Unit = println(page)

  class Connection {
    def get(url: String): String = {
      val random = new Random(System.nanoTime())
      if (random.nextBoolean()) "<html>...</html>"
      else throw new RuntimeException("Connection interrupted")
    }

    def getSafe(url: String): Try[String] = Try(get(url))
  }

  object HttpService {
    val random = new Random(System.nanoTime())

    def getConnection(host: String, port: String): Connection =
      if (random.nextBoolean()) new Connection
      else throw new RuntimeException("Someone else took the port")

    def getSafeConnection(host: String, port: String): Try[Connection] = Try(getConnection(host, port))
  }

  // if you get the html page from the connection, print it to the console i.e. call renderHTML
  HttpService.getSafeConnection(host, port).flatMap(c => c.getSafe("/home")).foreach(renderHTML)

  for {
    connection <- HttpService.getSafeConnection(host, port)
    html <- connection.getSafe("/home")
  } renderHTML(html)
}
