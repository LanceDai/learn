package cn.lancedai.learn.model.p

/**
  * @author LanceDai 
  * @date 2019/4/12 13:24
  * @description *
  */
object HelloWorld {

  class Outer {

    class Inner {
      private def f(): Unit = {
        println("f")
      }

      class InnerMost {
        f()
      }

    }

  }

  class Test {
    def m(x: Int): Int = x + 3

    val f: Int => Int = (x: Int) => x + 3
  }

  def main(args: Array[String]): Unit = {
    //    for (x <- 1 until 10; b <- 1 to 3) {
    //      println("x = " + x)
    //      println("b = " + b)
    //    }
    //    val numList = List(1, 2, 3, 4, 5, 6)
    //    val res = for {a <- numList if a % 3 == 1; if a != 5} yield a
    //    println(res)
    //    val t = new Test
    //    println(t.f(1))
    //    println(t.m(3))
    val buf = new StringBuilder
    buf += 'a'
    buf ++= "jdjasd"
  }
}
