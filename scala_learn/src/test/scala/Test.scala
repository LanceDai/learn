///**
//  * @author LanceDai
//  * @date 2019/4/12 14:56
//  * @description *
//  */
//trait Equal {
//  def isEqual(x: Any): Boolean
//
//  def isNotEqual(x: Any): Boolean = !isEqual(x)
//}
//
//class Point(val xc: Int, val yc: Int) extends Equal {
//  var x: Int = xc
//  var y: Int = yc
//
//  def move(dx: Int, dy: Int): Unit = {
//    x += dx
//    y += dy
//    println("x -> " + x)
//    println("y -> " + y)
//  }
//
//  override def isEqual(obj: Any): Boolean = obj.isInstanceOf[Point] && obj.asInstanceOf[Point].x == x
//}
//
//class Location(override val xc: Int, override val yc: Int, val zc: Int) extends Point(xc, yc) {
//  var z: Int = zc
//
//  def move(dx: Int, dy: Int, dz: Int): Unit = {
//    super.move(dx, dy)
//    z += dz
//    println("z -> " + z)
//  }
//}
//
//object Test {
//  def main(args: Array[String]): Unit = {
//    //    val pt = new Point(10, 20)
//    //    pt.move(10, 10)
//    //    val loc = new Location(10, 20, 30)
//    //    loc.move(1, 2, 3)
//    //    val p1 = new Point(2,3)
//    //    val p2 = new Point(2,4)
//    //    val p3 = new Point(4,3)
//    //
//    //    println(p1.isNotEqual(p2))
//    //    println(p1.isNotEqual(p3))
//    //    println(p1.isNotEqual(2))
//    import scala.io.Source._
//    lazy val file = fromFile("D:\\WorkSpace\\JavaWorkSpace\\learn\\scala_learn\\project\\build.properties").mkString
//
//  }
//}

class Person(name: String, age: Int) {
  def canEqual(a: Any): Boolean = a.isInstanceOf[Person]

  override def equals(that: Any): Boolean = {
    that match {
      case that: Person => that.canEqual(this) && that.hashCode() == this.hashCode()
      case _ => false
    }
  }

  override def hashCode(): Int = {
    val prime = 31
    var result = 1
    result = prime * result + age
    result = prime * result + (if (name == null) 0 else name.hashCode)
    result
  }
}

import org.scalatest.FunSuite

class PersonTests extends FunSuite {
  // these first two instances should be equal
  val nimoy = new Person("Leonard Nimoy", 82)
  val nimoy2 = new Person("Leonard Nimoy", 82)
  val shatner = new Person("William Shatner", 82)
  val ed = new Person("Ed Chigliak", 20)
  // all tests pass         
  test("nimoy == nimoy") {
    assert(nimoy == nimoy)
  }
  test("nimoy == nimoy2") {
    assert(nimoy == nimoy2)
  }
  test("nimoy2 == nimoy") {
    assert(nimoy2 == nimoy)
  }
  test("nimoy != shatner") {
    assert(nimoy != shatner)
  }
  test("shatner != nimoy") {
    assert(shatner != nimoy)
  }
  test("nimoy != null") {
    assert(nimoy != null)
  }
  test("nimoy != String") {
    assert(nimoy != "Leonard Nimoy")
  }
  test("nimoy != ed") {
    assert(nimoy != ed)
  }
}
