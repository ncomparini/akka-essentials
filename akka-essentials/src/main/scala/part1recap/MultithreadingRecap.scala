package part1recap

import scala.concurrent.Future
import scala.util.{Failure, Success}

object MultithreadingRecap extends App {

  val aThread = new Thread(()=> println("I'm running in parallel"))
  aThread.start()
  aThread.join()

  // unpredictable - different runs generates differents results
  val aThreadHello = new Thread(() => (1 to 1000).foreach(_ => println("Hellooo!!!")))
  val aThreadBye = new Thread(() => (1 to 1000).foreach(_ => println("Byeee!!!")))

  aThreadHello.start()
  aThreadBye.start()

  class BankAccount(private var amount: Int) {
    override def toString: String = "" + amount

    def withdraw(money: Int) = this.amount -= money

    def safeWithDraw(money: Int) = this.synchronized {
      this.amount -= money
    }
  }

  import scala.concurrent.ExecutionContext.Implicits.global
  val future = Future {
    42
  }

  future.onComplete {
    case Success(42) => println("I found the meaning of life!")
    case Failure(_) => println("something happened with the meaning of life!")
  }

  val aProcessedFuture = future.map(_ + 1)
  val aFlatFuture = future.flatMap {
    value => Future(value + 2)
  }

  val filteredFuture = future.filter(_ % 2 == 0)


  val aNonseneFuture = for {
    meaningOfLife <- future
    filteredMeaning <- filteredFuture
  } yield meaningOfLife + filteredMeaning



}
