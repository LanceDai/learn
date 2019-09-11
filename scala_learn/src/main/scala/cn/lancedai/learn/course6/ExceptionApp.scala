package cn.lancedai.learn.model.course6

/**
  * @author LanceDai 
  * @date 2019/4/13 16:12 
  * @description * 
  */
object ExceptionApp extends App {
  try {
    var i = 10 / 0
    println(i)
  }catch {
    case e:ArithmeticException => println(e.getMessage)
    case e:Exception => println(e.getLocalizedMessage)
  }finally {

  }

}
