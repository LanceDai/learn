package cn.lancedai.learn.actors

import akka.actor.{Actor, ActorRef, ActorSystem, Props}


class HelloActor extends Actor {
  override def receive: Receive = {
    case "hello" => println("hello back at you")
    case _ => println("huh? ")
  }
}

object Main extends App {
  // an actor needs an ActorSystem
  val system = ActorSystem("HelloSystem")

  // create and start the actor
  val helloActor = system.actorOf(Props[HelloActor], name = "hello_actor")

  // send the actor two messages
  helloActor ! "hello"
  helloActor ! "buenos dias"

  // shut down the system
  val res = system.terminate()
}

case object PingMessage

case object PongMessage

case object StartMessage

case object StopMessage

class Ping(pong: ActorRef) extends Actor {
  var count = 0

  def incrementAndPrint(): Unit = {
    count += 1
    println("ping")
  }

  override def receive: Receive = {
    case StartMessage =>
      incrementAndPrint()
      pong ! PingMessage
    case PongMessage =>
      incrementAndPrint()
      if (count > 99) {
        sender() ! StopMessage
        println("ping stopped")
        context.stop(self)
      } else {
        sender() ! PingMessage
      }
    case _ => println("Ping got something unexpected")
  }
}

class Pong extends Actor {
  override def receive: Receive = {
    case PingMessage =>
      println("Pong")
      sender() ! PongMessage
    case StopMessage =>
      println("pong stopped")
      context.stop(self)
    case _ => println("Pong got something unexpected")
  }
}

object PingPongTest extends App {
  val system = ActorSystem("PingPongSystem")
  val pong = system.actorOf(Props[Pong], name = "pong")
  val ping = system.actorOf(Props(new Ping(pong)), name = "ping")

  // start the action
  ping ! StartMessage
  // commented-out so you can see all the output
  system.terminate()
}


class Kenny extends Actor {
  println("entered the Kenny constructor")

  override def preStart(): Unit = {
    println("kenny: preStart")
  }

  override def postStop(): Unit = {
    println("kenny: postStop")
  }

  override def preRestart(reason: Throwable, message: Option[Any]): Unit = {
    println("kenny: preRestart")
    println(s" MESSAGE: ${message.getOrElse("")}")
    println(s" REASON: ${reason.getMessage}")
    super.preRestart(reason, message)
  }

  override def postRestart(reason: Throwable): Unit = {
    println("kenny: postRestart")
    println(s" REASON: ${reason.getMessage}")
    super.postRestart(reason)
  }

  override def receive: Receive = {
    case ForceRestart => throw new Exception("Boom! ")
    case _ => println("kenny received a message")
  }
}

case object ForceRestart

object LifecycleDemo extends App {
  val system = ActorSystem("LifecycleDemo")
  val kenny = system.actorOf(Props[Kenny], name = "Kenny")

  println("send kenny a simple String message")
  kenny ! "hello"
  Thread.sleep(1000)

  println("make kenny restart")
  kenny ! ForceRestart
  Thread.sleep(1000)

  println("stopping kenny")
  system.stop(kenny)


  println("shutting down system")

  system.terminate()
}