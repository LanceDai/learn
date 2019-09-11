package cn.lancedai.learn.model.course4

/**
  * @author LanceDai 
  * @date 2019/4/13 14:28 
  * @description * 
  */
object CaseClassApp {
  def main(args: Array[String]): Unit = {
    println(Dog("wangcai").name)
  }
}

case class Dog(name: String)
