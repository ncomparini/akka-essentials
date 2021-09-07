package part2actors

import akka.actor.{Actor, ActorSystem, Props}

object ActorsIntro extends App {

  // part 1: actor system
  val actorSystem = ActorSystem("firstAcctorSystem")
  println(actorSystem.name)

  // part2: create actors
  // word count actor

  class WordCountActor extends Actor {
    var totalWords = 0

    def receive: PartialFunction[Any, Unit] = {
      case message: String =>
        totalWords += message.split(" ").length
        println(s"[word counter] I have received: $message. Total of words: $totalWords")
      case msg => println(s"[word counter] I cannot understand ${msg.toString}")
    }
  }

  // part 3: instantiate our actor
  val wordCounter = actorSystem.actorOf(Props[WordCountActor], "wordCounter")
  val anotherWordCounter = actorSystem.actorOf(Props[WordCountActor], "anotherWordCounter")

  // part 4: communication
  wordCounter ! "I am learning Akka and it's pretty damn cool"
  wordCounter ! "A different message"

  object Person {
    def props(name: String) = Props(new Person(name))
  }

  class Person(name: String) extends Actor {
    override def receive: Receive = {
      case "hi" => println(s"Hi, my name is $name")
      case _ =>
    }
  }

  //val person = actorSystem.actorOf(Props(new Person("Bob"))) // valid initialization, but not a good practice
  val person = actorSystem.actorOf(Person.props("Bob")) // that's the good practice to instantiate the object
  person ! "hi"

}
