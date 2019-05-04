package course6

import scala.util.Random

/**
  * @author LanceDai 
  * @date 2019/4/13 15:46 
  * @description * 
  */
object MatchApp extends App {
  //  val names = Array("A", "b", "c")
  //
  //  val name = names(Random.nextInt(names.length))
  //
  //  name match {
  //    case "A" => println("jilaoshi...")
  //    case "b" => println("bolaoshi...")
  //    case _ => println("nnnn")
  //  }
  //
  //  def judgeGrade(grade: String): Unit = {
  //    grade match {
  //      case "A" => println("Excellent")
  //      case "B" => println("Good")
  //      case "C" => println("Just so so")
  //      case _ => println("you need study hard")
  //    }
  //  }
  //
  //  judgeGrade("A")
  //  judgeGrade("F")
  //  judgeGrade("C")
  //  judgeGrade("D")
  //
  //  def judgeGrade(name:String, grade: String): Unit = {
  //    grade match {
  //      case "A" => println("Excellent")
  //      case "B" => println("Good")
  //      case "C" => println("Just so so")
  //      case _ if name == "l" => println(name + " ... you are good boy")
  //      case _ => println("you need study hard")
  //    }
  //  }
  //
  //  judgeGrade("l", "A")
  //  judgeGrade("l", "F")

  //  def greeting(array: Array[String]): Unit = {
  //    array match {
  //      case Array("zhangsan") => println("Hi:zhangsan")
  //      case Array(x, y) => println(x + " ... " + y)
  //      case Array("zhangsan", _*) => println("other ...")
  //      case _ => println("everyone")
  //    }
  //  }
  //
  //  greeting(Array("zhangsan"))
  //  greeting(Array("lisi", "wangwu"))
  //  greeting(Array("zhangsan", "lisi", " wangwu "))

  //  def greeting(list: List[String]) = {
  ////    list match {
  ////      case "zhangsan" :: Nil => println("Hi zhangsan")
  //////      case x :: y :: Nil => println("Hi: " + x + " ... " + y)
  ////      case "zhangsan" :: tail => println("Hi: " + "zhangsan and other friends " + tail)
  ////      case _ => println("everyone")
  ////    }
  ////  }
  ////
  ////  greeting(List("zhangsan"))
  ////  greeting(List("lisi", "zhangsan"))
  ////  greeting(List("zhangsan", "lisi"))

  //  def matchType(obj: Any): Unit = {
  //    obj match {
  //      case x: Int => println("Int : " + x)
  //      case s: String => println("String : " + s)
  //      case m: Map[_, _] => m.foreach(println)
  //      case _ => println("other type")
  //    }
  //  }
  //
  //  matchType(Map("name" -> "PK"))
  //  matchType(1111)
  //  matchType("llll")

  def caseclassMatch(person: Person): Unit = {
    person match {
      case CTO(name, floor) => println("CTO name is " + name + " floor is " + floor)
      case Employee(name, floor) => println("Eployee name is " + name + " floor is " + floor)
      case Other(name, floor) => println("Other name is " + name + " floor is " + floor)
      case _ => println("other")
    }
  }

  class Person

  case class CTO(name: String, floor: String) extends Person

  case class Employee(name: String, floor: String) extends Person

  case class Other(name: String, floor: String) extends Person


  caseclassMatch(CTO("PK", "33"))
  caseclassMatch(Employee("zhangsan", "2"))
  caseclassMatch(Other("ssss", "3"))


}
