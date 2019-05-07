package cn.lancedai.learn.model.course4

/**
  * @author LanceDai 
  * @date 2019/4/13 13:43 
  * @description * 
  */

object SimpleObject {
  def main(args: Array[String]): Unit = {
    var people = new People()
    people.name = "Messi"
    println(people.name + "src " + people.age)
    people.eat()
  }
}

class People {
  var name: String = ""
  val age: Int = 10

  def eat(): String = {
    name + " eat..."
  }

  def watchFootball(teamName: String): Unit = {
    println(name + " is watching match of " + teamName)
  }
}
