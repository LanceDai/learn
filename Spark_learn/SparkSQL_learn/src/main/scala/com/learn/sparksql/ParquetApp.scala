package com.learn.sparksql

import org.apache.spark.sql.SparkSession

/**
  * @author LanceDai 
  * @date 2019/4/16 15:17 
  * @description DataSet操作
  */
object ParquetApp {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("dataSetApp")
      .master("local[2]")
      .getOrCreate()
//    val path = "D:\\WorkSpace\\JavaWorkSpace\\learn\\SparkSQL_learn\\src\\main\\resources\\users.parquet"
    var path = "/home/hadoop/data/users.parquet"

    val userDF = spark.read.format("parquet").load(path)
    userDF.printSchema()
    userDF.show()
    userDF.select("name", "favorite_color").write.format("json").save("/home/hadoop/data/users.json")

    spark.read.load(path).show()
    spark.stop()
  }
}
