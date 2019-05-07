package cn.lancedai.learn.model

/**
  * @author LanceDai 
  * @date 2019/5/4 10:01 
  * @description * 
  */
class Nat(val x: Int) {
  def get: Int = x

  def isEmpty: Boolean = x < 0

  override def toString: String = s"net value $x"
}

object Nat {
  def unapply(x: Int): Nat = new Nat(x)
}

object Main {
  def main(args: Array[String]): Unit = {
    5 match {
      case t@Nat(n) => println(s"$t value and ${t.getClass.getCanonicalName} class")
      case x => println(s"xxxxxxx")
    }
  }
}