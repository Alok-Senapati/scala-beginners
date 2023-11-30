package com.learning.misc

object BracelessSyntax extends App :
  /*
    - From Scala - 3, We can write functions, conditional, loops, classes, objects, traits, match-case without {}
    - But to define the scope properly, we must use proper indentation that is the inner block should have more
      indentation(spaces before) than the starting block
    - An option end statement can be written to denote ending of a block/expression
   */

  private val anIfExpression =
    if 2 > 3 then // then is only available since scala - 3, while using then parenthesis across the condition is not required
      val x = 10
      x + 4
    else
      val y = 20
      y + 9
  end anIfExpression
    // optional
  println(anIfExpression)

  private val aForComprehension =
    for
      x <- 1 to 10
      y <- List('a', 'b')
    yield s"$x$y"
  end aForComprehension
  println(aForComprehension)

  private def aFunction(x: Int): Int =
    val y = x * 10
    y + 10
  end aFunction
  println(aFunction(10))

  private val someVal: Any = "Scala"
  val aMatchCase: Unit = someVal match
    case x: Int => println(s"$x is an Int")
    case y: Boolean => println(s"$y is a Boolean")
    case z: Double => println(s"$z is a Double")
    case x: String => println(s"$x is an String")
    case _ => println("Something Else")
  end aMatchCase

  class Animal: // : is required for classes, objects, enums, traits etc
    def eat(): Unit =
      println("I'm eating.")
    end eat

    def grow(): Unit =
      println("I'm growing")
    end grow
  end Animal
  val anAnimal = new Animal
  anAnimal.eat()

  // Anonymous Class
  private val anAnonymousClass = new Animal:
    override def eat(): Unit =
      println("Override Eat")
    end eat
  end anAnonymousClass
  anAnonymousClass.eat()
  // All the end statements are optional
end BracelessSyntax

