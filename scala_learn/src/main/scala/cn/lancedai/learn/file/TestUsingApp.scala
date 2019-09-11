package cn.lancedai.learn.file

import Control._

object TestUsingApp extends App {
  //  using(io.Source.fromFile("/home/lance/IdeaProjects/github/learn/scala_learn/src/main/resources/Preded.scala")) {
  //    resource => {
  //      for (line <- resource.getLines()) {
  //        println(line)
  //      }
  //    }
  //  }

  val filename = "/home/lance/IdeaProjects/github/learn/scala_learn/src/main/resources/Preded.scala"
  readTextFile(filename) match {
    case x@Some(_) => println(s"size = ${x.get.count(x => x.trim != "")}\ncontent = ${x.get.filter(x => x.trim != "").mkString("[", ",\n", "]")}")
    case x@None => println(s"wrong and res is empty = ${x.isEmpty}")
    case _ => println("something unknown")
  }

  def readTextFile(filename: String): Option[List[String]] = {
    try {
      val lines = using(io.Source.fromFile(filename)) {
        resource => (for (line <- resource.getLines) yield line).toList
      }

      Some(lines)
    } catch {
      case e: Exception => None
    }
  }
}
