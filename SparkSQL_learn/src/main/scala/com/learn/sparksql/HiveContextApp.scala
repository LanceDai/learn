package com.learn.sparksql

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.hive.HiveContext

/**
  * @author LanceDai 
  * @date 2019/4/15 17:15 
  * @description * 
  */
object HiveContextApp {
  def main(args: Array[String]): Unit = {
    //1)创建相应的Context
    //在测试或者生产中，AppName和Master，可以是通过脚本进行指定
    val sparkSession = SparkSession
      .builder()
      .appName("HiveContextApp")
      .master("local[2]")
      .enableHiveSupport()
      .getOrCreate()
    val hiveContext = sparkSession.sqlContext
    //2) 相关的处理: json
    hiveContext.table("wordcount")
    //3) 关闭资源
    sparkSession.stop()
  }
}
