package cn.lancedai.learn.file
import java.io.File

import Util._
object FileApp extends App {

  import scala.io.Source

  val filename = "/home/lance/IdeaProjects/github/learn/scala_learn/src/main/resources/Preded.scala"
  val file = Source.fromFile(filename)
  for ((line, count) <- file.getLines().zip(Stream from 1)) {
    println(s"$count : $line")
  }
  file.close()

  (Stream from 1).printDetail()
}


object FileWriterApp extends App{
  val filename = "/home/lance/IdeaProjects/github/learn/scala_learn/src/main/resources/hello.txt"
  // PrintWriter
  import java.io._
  val pw = new PrintWriter(new File(filename))
  pw.write("hello, world")
  pw.close()

  // FileWriter
  val file = new File(filename)
  val bw = new BufferedWriter(new FileWriter(file))
  bw.append("Hello Hello Hello")
  bw.close()
}

object FileListApp extends App{
  def getListOfFiles(dir:String):List[File] = {
    val d = new File(dir)
    if(d.exists() && d.isDirectory){
      d.listFiles.filter(_.isFile).toList
    }else{
      List[File]()
    }
  }
  getListOfFiles("/tmp").foreach(println)
}

import scala.sys.process._
object OuterCommandApp extends App{
//  "ls -al".!
  val res = Seq("/bin/sh", "-c", "ls *.scala").!!
  println(res)
}
