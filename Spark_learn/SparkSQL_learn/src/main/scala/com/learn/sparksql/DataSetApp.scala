package com.learn.sparksql

import org.apache.spark.sql.SparkSession

/**
  * @author LanceDai 
  * @date 2019/4/16 15:17 
  * @description DataSet操作
  */
object DataSetApp {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("dataSetApp")
      .master("local[2]")
      .getOrCreate()
    //spark如何解析csv文件
    // val path = "D:\\WorkSpace\\JavaWorkSpace\\learn\\SparkSQL_learn\\src\\main\\resources\\sales.csv"
    val path = "/home/hadoop/data/sales.csv"
    val df = spark.read.option("header", "true").option("inferSchema", "true").csv(path)
    df.show()
    import spark.implicits._
    val ds = df.as[Sales]
    ds.map(line => line.itemId).show()
    spark.stop()
  }

  case class Sales(transactionId: Int, customerId: Int, itemId: Int, amountPaid: Double)

}
