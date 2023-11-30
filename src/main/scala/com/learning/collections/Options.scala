package com.learning.collections

import scala.util.Random

object Options extends App {
  /*
    Option -
    - Options are used to avoid NullPointerExceptions
    - It's the function way of dealing with the scenarios of possible absence of a value
    - It's a wrapper for a value that might be present or not
    - It also deals with unsafe APIs
    - It can contain 2 values:
      - Some(value) -> Indicating presence of a value
      - None -> Indicating absence of a value
   */

  val anOptionWithValue: Option[Int] = Some(4)
  val anOptionWithoutValue: Option[Int] = None
  println(anOptionWithValue)
  println(anOptionWithoutValue)

  // Uses of options -->
  // .get method in Map returns an Option, where Some(value) is the value if the key is present or else returns None
  val aMap: Map[Int, String] = Map(1 -> "One", 2 -> "Two", 3 -> "Three")
  println(aMap.get(1))
  println(aMap.get(4))

  // .headOption in List returns an Option based on the presence of head (emptiness)
  println(List().headOption)
  println(List(1, 2).headOption)

  // Unsafe APIs/Methods
  // Consider an unsafe method that can return null
  def unsafeMethod: String = null
  // We can handle it and avoid NullPointerException using Options
  val unsafeOutput = Option(unsafeMethod)  // Some(unsafeMethod) is not used as it will return Some(null) which breaks the purpose of using Option
  println(unsafeOutput)

  // The above approach is mostly used in method chaining
  // Suppose we have a safeMethod which we needs to run if unsafeMethod returns null
  def safeMethod: String = "A valid String"
  val chainedResult = Option(unsafeMethod).orElse(Option(safeMethod))
  println(chainedResult)

  // The more appropriate implementation will be to use Option as return type for the functions with optional output
  def betterUnsafeMethod: Option[String] = None
  def betterSafeMethod: Option[String] = Some("A valid String")
  val betterChainedResult = betterUnsafeMethod orElse betterSafeMethod
  println(betterChainedResult)

  // Option functions
  println(anOptionWithValue.isEmpty) // Will return true if an Option is None
  // println(anOptionWithoutValue.get) // Unsafe as it will throw RTE for a None value
  println(anOptionWithoutValue.getOrElse(20))
  println(anOptionWithValue.getOrElse(-1))

  // map, filter, flatMap
  println(anOptionWithValue.map(_ * 2))
  println(anOptionWithoutValue.map(_ * 2))  // Returns None

  println(anOptionWithValue.filter(_ % 2 != 0))  // Returns None if the predicate returns false

  println(anOptionWithValue.flatMap(x => Option(x + 10)))
  println(anOptionWithoutValue.flatMap(x => Option(x + 10)))  // Returns None

  /*
    Exercise -
   */
  val config: Map[String, String] = Map(
    "host" -> "192.128.1.1",
    "port" -> "22"
  )

  class Connection(host: String, port: String) {
    def connect: String = s"Connection established with host: $host with port: $port"
  }

  object Connection {
    val random = new Random(System.nanoTime())
    def apply(host: String, port: String): Option[Connection] = {
      if random.nextBoolean() then Some(new Connection(host, port))
      else None
    }
  }

  // Try to establish a connection if so print the connect method
  // Tip - Always use flatMap(f) if for a Collection f returns a Collection or else use map(f) if for a Collection f returns a value such as Int, String etc.
  config.get("host").flatMap(h => config.get("port").flatMap(p => Connection(h, p))).map(c => c.connect).foreach(println)

  // Using for comprehensions
  val connectionStatus = for {
    host <- config.get("host")
    port <- config.get("port")
    connection <- Connection(host, port)
  } yield connection.connect
  println(connectionStatus)
}
