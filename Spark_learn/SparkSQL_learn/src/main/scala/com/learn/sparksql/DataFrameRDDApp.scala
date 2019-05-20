package com.learn.sparksql

import org.apache.spark.sql.{DataFrame, SparkSession}

/**
  * @author LanceDai 
  * @date 2019/4/16 13:26 
  * @description DataFrame api 基本操作
  */
object DataFrameRDDApp {
  def main(args: Array[String]): Unit = {
    val sparkSession = SparkSession
      .builder()
      .appName("DataFrameRDD2App")
      .master("local[2]")
      .getOrCreate()
    //2) 相关的处理: text
    //RDD => DataFrame
    val path = "D:\\WorkSpace\\JavaWorkSpace\\learn\\SparkSQL_learn\\src\\main\\resources\\infos.txt"
    val infosRDD = sparkSession
      .sparkContext
      .textFile(path)
    import sparkSession.implicits._
    val infoDF: DataFrame = infosRDD.map(_.split(",")).map(line => Info(line
    (0).toInt, line(1), line(2))).toDF()
    infoDF.show()
    infoDF.filter(infoDF.col("age") > 30).show()
    infoDF.createOrReplaceTempView("infos")
    sparkSession.sql("select * from infos where age > 30").show()
    //3) 关闭资源
    sparkSession.stop()
  }

  case class Info(id: Int, name: String, age: String)

}
