


package cn.lancedai.learn.model.p {

  class Super {
    protected def f(): Unit = {
      print("f")
    }

    class Sub extends Super {
      f()
    }
  }

  class Other {

  }
}
