package cn.lancedai.learn

import org.scalatest.FunSuite

class HelloTests extends FunSuite {
  test("the name is set correctly in constructor") {
    val p = Person("LanceDai")
    assert(p.name == "LanceDai")
  }

  test("a Person's name can be changed") {
    val p = Person("Chad Johnson")
    p.name = "Ochocinco"
    assert(p.name == "Ochocinco")
  }
}
