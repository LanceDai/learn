package cn.lancedai.learn.factory

import cn.lancedai.learn.factory.Animal.{Cat, Dog}

trait Animal {
  def speak
}

object Animal {

  private[factory] class Dog extends Animal {
    override def speak: Unit = {
      println("woof")
    }
  }

  private[factory] class Cat extends Animal {
    override def speak: Unit = {
      println("meow")
    }
  }

  //the factory method
  def apply(cls: Class[_ <: Animal]): Animal = cls.newInstance()
}

object FactoryApp extends App {
  val cat = Animal(classOf[Cat])
  val dog = Animal(classOf[Dog])
  //  val str = Animal(classOf[String])
  cat.speak
  dog.speak
  //  str.speak
}
