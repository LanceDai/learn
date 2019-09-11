package com.learn.sparksql

import org.apache.spark.sql.SparkSession

/**
  * @author LanceDai 
  * @date 2019/4/16 13:26 
  * @description DataFrame api 基本操作
  */
object DataFrameApp {
  def main(args: Array[String]): Unit = {
    val sparkSession = SparkSession
      .builder()
      .appName("DataFrameRDD2App")
      .master("local[2]")
      .getOrCreate()
    //2) 相关的处理: json
//    val path = "D:\\WorkSpace\\JavaWorkSpace\\learn\\SparkSQL_learn\\src\\main\\resources\\people.json"
//    val peopleDF = sparkSession.read.json(path)
//    peopleDF.printSchema()
//    peopleDF.show()
//
//    peopleDF.select("name").show()
//    peopleDF.select(peopleDF.col("name")).show()
//    peopleDF.select(peopleDF.col("name"),
//      (peopleDF.col("age") + 10).as("newAge")).show()
//    peopleDF.filter(peopleDF.col("age") > 19).show()
//    peopleDF.groupBy("age").count().show()
    //3) 关闭资源
    sparkSession.stop()
  }
}
