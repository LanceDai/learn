package cn.lancedai.learn.file

object Control {
  def using[A <: {def close() : Unit}, B](resource: A)(f: A => B): B =
    try {
      f(resource)
    } finally {
      resource.close()
      println("resource is close!!!")
    }
}
