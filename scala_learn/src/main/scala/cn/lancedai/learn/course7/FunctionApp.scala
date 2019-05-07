package cn.lancedai.learn.model.course7

import scala.io.Source._

/**
  * @author LanceDai 
  * @date 2019/4/14 10:34 
  * @description * 
  */
object FunctionApp extends App {
  //  var fun = (x: Int) => x + 1
  //
  //  println(fun(5))

  var l = List(1, 2, 3, 4, 5, 6, 7, 8)
  //  l = l.map((x: Int) => x + 1)
  //  l = l.map(x => x + 1)
  //  l = l.map(_ + 1)
  //  l.foreach(println)

  //  l.filter(_ > 4).foreach(println)
  //  println(l.reduce(_ + _))
  //  l.reduceLeft(_ - _)
  //  l.reduceRight(_ - _)
  //  l.fold(0)(_ - _)
  //  l.foldRight()
  //  println(l.sum)
  //  val f = List(List(List(1, 2), List(3, 4), List(5, 6)))
  //  println(f.flatten)
  //  println(f.flatMap(_.map(_.map(_ * 2))))

  //  val txtFile = fromFile("D:\\WorkSpace\\JavaWorkSpace\\learn\\scala_learn\\src\\scala\\cn.lancedai.learn.model.course7\\Hello.txt")
  //  val txt = txtFile.mkString
  //  txtFile.close()
  //
  //  val txts = List(txt)
  //  println(txts)
  //  txts.flatMap(_.split(",")).map(x => (x, 1)).groupBy(_._1).mapValues(_.size).foreach(println)

  //  def sum(a: Int, b: Int) = a + b
  //
  //  println(sum(2, 3))
  //
  //  def sum2(a: Int)(b: Int) = a + b
  //
  //  println(sum2(2)(3))
  //  def func(msg: String): Unit = println(msg)
  //  implicit def intToString(int:Int): String = String.valueOf(int) + " from Int"
  //  func(11)
  def ceil(x: Double): Double = java.lang.Math.ceil(x)

  def valueForCeil(f: Double => Double, value: Double) = {
    f(value)
  } //> valueForCeil: (f: Double => Double, value: Double)Double
  println(valueForCeil(ceil, 0.25)) //> res0: Double = 1.0

  l.collect({ case i => i + 1 })
}
