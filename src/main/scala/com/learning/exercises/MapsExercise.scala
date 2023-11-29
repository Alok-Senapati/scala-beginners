package com.learning.exercises

import org.scalactic.anyvals.NonEmptyMap
import org.scalatest.tags.Network

import scala.annotation.tailrec

object MapsExercise extends App {
  /*
     1.  What would happen if I had two original entries "Jim" -> 555 and "JIM" -> 900 and apply toLowerCase on map

         !!! careful with mapping keys.

     2.  Overly simplified social network based on maps
         Person = String
         - add a person to the network
         - remove
         - friend (mutual)
         - unfriend

         - number of friends of a person
         - person with most friends
         - how many people have NO friends
         - if there is a social connection between two people (direct or not)
    */

  // 1. Solution
  val phoneBook = Map(
    "Alok" -> 656756768,
    "John" -> 675476754,
    "Jim" -> 4354654,
    "JIM" -> 675645
  )

  println(phoneBook.map(pair => pair._1.toLowerCase() -> pair._2))

  // 2. Solution:
  def add(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = network + (person -> Set())

  private def friend(network: Map[String, Set[String]], person1: String, person2: String): Map[String, Set[String]] = {
    val friendsPerson1 = if network.contains(person1) then network(person1) else Set[String]()
    val friendsPerson2 = if network.contains(person2) then network(person2) else Set[String]()

    network + (person1 -> (friendsPerson1 + person2)) + (person2 -> (friendsPerson2 + person1))
  }

  private def unfriend(network: Map[String, Set[String]], person1: String, person2: String): Map[String, Set[String]] = {
    if !network.contains(person1) then throw new RuntimeException(s"$person1 does not exist in the network")
    if !network.contains(person2) then throw new RuntimeException(s"$person2 does not exist in the network")

    val friendsPerson1 = network(person1)
    val friendsPerson2 = network(person2)

    network + (person1 -> (friendsPerson1 - person2)) + (person2 -> (friendsPerson2 - person1))
  }

  private def remove(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
    if !network.contains(person) then throw new RuntimeException(s"$person does not exist in the network")

    @tailrec
    def removeAux(friends: Set[String], network: Map[String, Set[String]]): Map[String, Set[String]] = {
      if friends.isEmpty then network
      else removeAux(friends.tail, unfriend(network, person, friends.head))
    }

    removeAux(network(person), network) - person
  }

  private def friendsCount(network: Map[String, Set[String]], person: String): Int = {
    if !network.contains(person) then 0
    else network(person).size
  }

  private def personWithMostFriends(network: Map[String, Set[String]]): String = {
    network.maxBy(pair => pair._2.size)._1
  }

  private def countPersonWithNoFriends(network: Map[String, Set[String]]): Int = {
    network.count(pair => pair._2.isEmpty)
  }

  private def isConnected(network: Map[String, Set[String]], person1: String, person2: String): Boolean = {
    @tailrec
    def bfs(target: String, consideredPeople: Set[String], discoveredPeople: Set[String]): Boolean = {
      if discoveredPeople.isEmpty then false
      else {
        val people = discoveredPeople.head
        if people == target then true
        else if consideredPeople.contains(people) then bfs(target, consideredPeople, discoveredPeople.tail)
        else bfs(target, consideredPeople + people, discoveredPeople.tail ++ network(people))
      }
    }

    bfs(person2, Set(), network(person1) + person1)
  }


  // Tests
  private var network: Map[String, Set[String]] = Map()
  println(network)

  network = add(network, "Jack")
  network = add(network, "Mary")
  network = add(network, "Harry")
  network = add(network, "Jim")
  network = add(network, "Tom")
  network = add(network, "Jerry")
  network = add(network, "Albert")
  println(network)

  network = friend(network, "Jack", "Jim")
  network = friend(network, "Jack", "Mary")
  network = friend(network, "Jack", "Tom")
  network = friend(network, "Tom", "Jerry")
  network = friend(network, "Harry", "Mary")
  println(network)

  println(unfriend(network, "Jack", "Mary"))
  println(remove(network, "Jerry"))

  println(friendsCount(network, "Jerry"))
  println(friendsCount(network, "Tom"))
  println(friendsCount(network, "Jack"))

  println(personWithMostFriends(network))
  println(countPersonWithNoFriends(network))

  println(isConnected(network, "Tom", "Mary"))
  println(isConnected(network, "Harry", "Jerry"))
  println(isConnected(network, "Harry", "Albert"))
}
