package com.learn.sparksql

import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.{DataFrame, Row, SparkSession}

/**
  * @author LanceDai 
  * @date 2019/4/16 13:26 
  * @description DataFrame api 基本操作
  */
object DataFrameRDD2App {
  def main(args: Array[String]): Unit = {
    val sparkSession = SparkSession
      .builder()
      .appName("DataFrameRDD2App")
      .master("local[2]")
      .getOrCreate()
    //2) 相关的处理: text
    //RDD => DataFrame
    val path = "D:\\WorkSpace\\JavaWorkSpace\\learn\\SparkSQL_learn\\src\\main\\resources\\infos.txt"
    val infos = sparkSession
      .sparkContext
      .textFile(path)
    val infosRDD = infos
      .map(_.split(","))
      .map(line => Row(line
      (0).toInt, line(1), line(2).toInt))
    val structType = StructType(Array(
      StructField("id", IntegerType),
      StructField("name", StringType),
      StructField("age", IntegerType)))
    val infosDF = sparkSession.createDataFrame(infosRDD, structType)
    infosDF.show()
    infosDF.printSchema()
    infosDF.filter(infosDF.col("age") > 30).show()
    infosDF.createOrReplaceTempView("infos")
    sparkSession.sql("select * from infos where age > 30").show()
    //3) 关闭资源
    sparkSession.stop()
  }


}
