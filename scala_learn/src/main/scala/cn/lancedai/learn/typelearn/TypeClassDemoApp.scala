package cn.lancedai.learn.typelearn

import cn.lancedai.learn.factory.Animal

import scala.collection.mutable.ArrayBuffer

trait Animal

final case class Dog(name: String) extends Animal

final case class Cat(name: String) extends Animal

object TypeClassDemoApp extends App {

  trait HumanLike[-A] {
    def speak(speaker: A): Unit
  }

  object HumanLike {

    implicit object AnimalIsHumanLike extends HumanLike[Animal] {
      override def speak(animal: Animal): Unit = {
        println(s"I'm a ${animal.getClass.getSimpleName}")
      }
    }

  }


  // 传入的是Dog Cat这种Animal的子类,对于逆变来说,父类是可以运行的,所以找父类的定义,存在则进行隐式转换
  def makeHumanLikeThingSpeak[A](animal: A)(implicit humanLike: HumanLike[A]): Unit = {
    humanLike.speak(animal)
  }

  makeHumanLikeThingSpeak(Dog("Rover"))

  makeHumanLikeThingSpeak(Cat("Morris"))
}
