/* SimpleApp.java */

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.FilterFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;

/**
 * @author LanceDai
 * @date 2019/1/18 10:22
 * @description *
 */

public class SimpleApp {
    public static void main(String[] args) {
        // Should be some file on your system
        String logFile = "D:\\Spark\\spark-2.4.0-bin-hadoop2.7\\README.md";
        SparkConf conf = new SparkConf().setAppName("Simple Application").setMaster("local");
        SparkSession spark = SparkSession.builder().config(conf).getOrCreate();
        Dataset<String> logData = spark.read().textFile(logFile).cache();

        long numAs = logData.filter((FilterFunction<String>) s -> s.contains("a")).count();
        long numBs = logData.filter((FilterFunction<String>) s -> s.contains("b")).count();

        System.out.println("Lines with a: " + numAs + ", lines with b: " + numBs);

        spark.stop();
    }
}