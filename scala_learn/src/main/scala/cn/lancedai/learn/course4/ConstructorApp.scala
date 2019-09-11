package cn.lancedai.learn.model.course4

/**
  * @author LanceDai 
  * @date 2019/4/13 13:52 
  * @description * 
  */
object ConstructorApp {
  def main(args: Array[String]): Unit = {
//    val person = new Person("zhangsan", 30)
//    println(person.name + " ... " + person.age + " ... " + person.school)
//    val person2 = new Person("PK", 18, "male")
//    println(person2.name + person2.age + person2.gender)

    val student = new Student("PK", 18, "Math")
    println(student)
  }
}

class Person(val name: String, val age: Int) {
  println("Person Constructor enter..." + name)

  val school = "ustc"
  var gender: String = _

  def this(name: String, age: Int, gender: String) {
    this(name, age)
    this.gender = gender
  }

  println("Person Constructor leave...")

  override def toString = s"Person(school=$school, gender=$gender, name=$name, age=$age)"
}

class Student(name: String, age: Int, val major: String)
  extends Person(name, age) {
  println("Person Student Constructor enter...")
  println("Person Student Constructor enter...")


  override def toString = s"Student(major=$major, name=$name, age=$age, school=$school)"
}
