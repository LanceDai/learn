package com.learn.sparksql

import org.apache.spark.sql.SparkSession

/**
  * @author LanceDai 
  * @date 2019/4/15 15:07 
  * @description * 
  */
object SQLContextApp {
  def main(args: Array[String]): Unit = {
    //1)创建相应的Context
    //在测试或者生产中，AppName和Master，可以是通过脚本进行指定
    val sparkSession = SparkSession
      .builder()
      //      .appName("SparkSessionApp")
      //      .master("local[2]")
      .getOrCreate()
    val sqlContext = sparkSession.sqlContext
    println(sqlContext.getClass.getName)
    //2) 相关的处理: json
    //    val path = "D:\\WorkSpace\\JavaWorkSpace\\learn\\SparkSQL_learn\\src\\main\\resources\\people.json"
    val path = args(0)
    val people = sqlContext.read.format("json").load(path)
    people.printSchema()
    people.show()
    //3) 关闭资源
    sparkSession.stop()
  }
}
