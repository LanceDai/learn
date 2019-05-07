package cn.lancedai.learn.model

object MainDriver extends App {
  // access our method, constant, and enumeration
  echo("Hello, world")
  echo(MAGIC_NUM)
  echo(Margin.LEFT)
  var r = Margin.LEFT
  println(r.getClass.getCanonicalName)
  Margin.values.foreach(println)

  //use our MutableMap type (scala.collection.mutable.Map)
  val mm: MutableMap[String, String] = MutableMap[String, String]("name" -> "AI")
  mm += ("password" -> "123")
  for ((k, v) <- mm) printf("key: %s, value: %s\n", k, v)
}
