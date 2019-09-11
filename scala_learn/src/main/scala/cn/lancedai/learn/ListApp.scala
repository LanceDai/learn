package cn.lancedai.learn

object RightAssociativeExample extends App {
  val f1 = new Printer
  f1 >> 42
  42 >>: f1
}

class Printer {
  def >>(i: Int) {
    println(s"$i")
  }

  def >>:(i: Int) {
    println(s"$i")
  }
}
