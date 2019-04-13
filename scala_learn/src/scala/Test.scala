/**
  * @author LanceDai 
  * @date 2019/4/12 14:56 
  * @description * 
  */
trait Equal {
  def isEqual(x: Any): Boolean

  def isNotEqual(x: Any): Boolean = !isEqual(x)
}

class Point(val xc: Int, val yc: Int) extends Equal {
  var x: Int = xc
  var y: Int = yc

  def move(dx: Int, dy: Int): Unit = {
    x += dx
    y += dy
    println("x -> " + x)
    println("y -> " + y)
  }

  override def isEqual(obj: Any): Boolean = obj.isInstanceOf[Point] && obj.asInstanceOf[Point].x == x
}

class Location(override val xc: Int, override val yc: Int, val zc: Int) extends Point(xc, yc) {
  var z: Int = zc

  def move(dx: Int, dy: Int, dz: Int): Unit = {
    super.move(dx, dy)
    z += dz
    println("z -> " + z)
  }
}

object Test {
  def main(args: Array[String]): Unit = {
    //    val pt = new Point(10, 20)
    //    pt.move(10, 10)
//    val loc = new Location(10, 20, 30)
//    loc.move(1, 2, 3)
//    val p1 = new Point(2,3)
//    val p2 = new Point(2,4)
//    val p3 = new Point(4,3)
//
//    println(p1.isNotEqual(p2))
//    println(p1.isNotEqual(p3))
//    println(p1.isNotEqual(2))
    import scala.io.Source._
    lazy val file = fromFile("D:\\WorkSpace\\JavaWorkSpace\\learn\\scala_learn\\project\\build.properties").mkString

  }
}
