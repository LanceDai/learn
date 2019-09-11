package cn.lancedai.learn

import scala.collection.mutable

package object model {
  // field
  val MAGIC_NUM = 42

  //method
  def echo(a: Any): Unit = println(a)

  //enumeration
  object Margin extends Enumeration {
    type Margin = Value
    //    val TOP, BUTTON, LEFT, RIGHT = Value
    val TOP: model.Margin.Value = Value(1)
    val BUTTON: model.Margin.Value = Value(2)
    val LEFT: model.Margin.Value = Value(3)
    val RIGHT: model.Margin.Value = Value(4)
  }

  //type definition
  type MutableMap[K, V] = scala.collection.mutable.Map[K, V]
  val MutableMap: mutable.Map.type = scala.collection.mutable.Map

}
