package part1recap

import scala.util.Try

object GeneralRecap extends App {

  val aCondition: Boolean = false
  var aVariable = 42
  aVariable += 1

  // expressions
  val aConditionedVal = if (aCondition) 42 else 65

  // code block
  val aCodeBlock = {
    if (aCondition) 74
    56
  }


  // TYPES
  // Unit
  val theUnit = println("Hello, això és Scala Nadal")

  def aFunction(x: Int): Int = x + 1
  // tail recursion
  def factorial(n:Int, acc: Int): Int =
    if (n <= 0) acc
    else factorial(n - 1, n * acc)

  // OOP
  class Animal
  class Dog extends Animal
  val aDog: Animal = new Dog

  trait Carnivore {
    def eat(a: Animal): Unit
  }

  class Crocodile extends Animal with Carnivore {
    override def eat(a: Animal): Unit = println("crunch!")
  }

  // method notations
  val aCroc = new Crocodile
  aCroc.eat(aDog)
  aCroc eat aDog

  // anonymous classes
  val aCarnivore = new Carnivore {
    override def eat(a: Animal): Unit = println("roaaaaaar")
  }

  aCarnivore eat aDog

  // generics
  abstract class MyList[+A] // covariant
  // companion objects
  object MyList

  // case classes
  case class Person(name: String, age: Int)  // IMPORTANT!!!

  // Exceptions
  val aPotentialFailure = try {
    throw new RuntimeException("I'm innocent, I swear!") // returns NOTHING (class)
  } catch {
    case e: Exception => "I CAUGHT AN EXCEPTION"
  } finally {
    // side effects
    println("some logs")
  }

  // Functionl programming
  val incrementer = new Function[Int, Int] {
    override def apply(v1: Int): Int = v1 + 1
  }

  val incremented = incrementer(42)
  //incrementer.apply(42)

  val anonymousIncrementer = (x: Int) => x + 1
  // Int => Int == Function1[Int, Int]

  // Functional programming is all about working with functions as first-class
  List(1,2,3).map(incrementer)
  // map = HOF (High Order Functions)

  // for comprehensions
  val pairs = for {
    num <- List(1,2,3)
    char <- List('a','b','c','d')
  } yield num + '-' + char
  //List(1,2,3,4).flatMap(num => List('a','b','c','d').map(char => num + '-' + char))

  // collections
  // Option and Try
  val anOption = Some(2)
  val aTry = Try{
    throw new RuntimeException
  }

  // pattern matching
  val unknown = 2
  val order = unknown match {
    case 1 => "first"
    case 2 => "second"
    case _ => "unknown"
  }

  val bob = Person("Bob", 22)
  val greeting = bob match {
    case Person(n, _) => s"Hi, my name is $n"
    case _ => "Idk my name"
  }

}
