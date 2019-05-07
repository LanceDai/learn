package cn.lancedai.learn

object Main extends App {
  val p = Person("LanceDai")
  println("Hello from " + p.name)
}

case class Person(var name: String)