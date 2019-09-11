package com.learn.sparksql

import org.apache.spark.sql.{DataFrame, SparkSession}

/**
  * @author LanceDai 
  * @date 2019/4/16 14:26 
  * @description DataFrame其他操作
  */
object DataFrameCaseApp {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .appName("DataFrameRDD2App")
      .master("local[2]")
      .getOrCreate()
    //    val path = "D:\\WorkSpace\\JavaWorkSpace\\learn\\SparkSQL_learn\\src\\main\\resources\\student.data"
    val path = "/home/hadoop/data/student.data"
    import spark.implicits._
    val studentRDD = spark.sparkContext.textFile(path)
    val studentDF = studentRDD
      .map(_.split("\\|"))
      .map(line => Student(line(0).toInt, line(1), line(2), line(3)))
      .toDF()
    //    studentDF.show()
    studentDF.show(false)
    studentDF.take(10)
    studentDF.first()
    studentDF.filter("name = ''").show()
    studentDF.sort(studentDF("name")).show()
    studentDF.sort(studentDF("name").desc).show()
    studentDF.sort("name", "id").show()
    studentDF.sort(studentDF("name").asc, studentDF("id").desc).show()
    val studentDF2 = studentRDD
      .map(_.split("\\|"))
      .map(line => Student(line(0).toInt, line(1), line(2), line(3)))
      .toDF()
    studentDF.join(studentDF2, studentDF.col("id")===studentDF2.col("id")).show()
    spark.stop()
  }

  case class Student(id: Int, name: String, phone: String, email: String)

}
