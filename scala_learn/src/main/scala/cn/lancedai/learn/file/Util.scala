package cn.lancedai.learn.file

object Util {
  implicit def streamToIterator(stream: Stream[_]): Iterator[_] = stream.iterator

  implicit class StreamUtil(val stream: Stream[_]) {
    def printDetail(): Unit = println(stream.toString())
  }

}
