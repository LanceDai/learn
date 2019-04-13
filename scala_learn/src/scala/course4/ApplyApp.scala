package course4

/**
  * @author LanceDai 
  * @date 2019/4/13 14:17 
  * @description * 
  */
object ApplyApp {
  def main(args: Array[String]): Unit = {
    //    for (i <- 1 to 10){
    //      ApplyTest.incr()
    //    }
    //    println(ApplyTest.count)

    val b = ApplyTest()
  }
}

//伴生类
class ApplyTest {
  def apply(): ApplyTest = {
    println("Class ApplyTest Apply")
    new ApplyTest()
  }
}

//伴生对象
//单例对象
object ApplyTest {
  println("Object ApplyTest enter")
  var count = 0

  def incr(): Unit = {
    count += 1
  }

  println("Object ApplyTest leave")

  def apply(): ApplyTest = {
    println("Object ApplyTest Apply")
    new ApplyTest()
  }
}
