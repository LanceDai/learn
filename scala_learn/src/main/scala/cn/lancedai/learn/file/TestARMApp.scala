package cn.lancedai.learn.file

import resource._

object TestARMApp extends App {
  for (source <- managed(scala.io.Source.fromFile("/home/lance/IdeaProjects/github/learn/scala_learn/src/main/resources/Preded.scala"))) {
    for (line <- source.getLines()) {
      println(line)
    }
  }
}


