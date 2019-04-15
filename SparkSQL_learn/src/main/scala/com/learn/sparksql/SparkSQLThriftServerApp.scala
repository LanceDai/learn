package com.learn.sparksql

import java.sql.{Connection, DriverManager}

/**
  * @author LanceDai 
  * @date 2019/4/15 19:01 
  * @description 通过jdbc访问
  */
object SparkSQLThriftServerApp {
  def main(args: Array[String]): Unit = {
    Class.forName("org.apache.hive.jdbc.HiveDriver")
    val conn: Connection = DriverManager.getConnection("jdbc:hive2://192.168.136.21:10000", "hadoop", "")
    val pstmt = conn.prepareStatement("select * from hive_wordcount")
    val rs = pstmt.executeQuery()
    var count: Int = 0
    while (rs.next()) {
      println(count + rs.getString("context"))
      count = count + 1
    }
    rs.close()
    pstmt.close()
    conn.close()
  }
}
