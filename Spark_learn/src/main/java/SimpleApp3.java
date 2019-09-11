/* SimpleApp.java */

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.FilterFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

/**
 * @author LanceDai
 * @date 2019/1/18 10:22
 * @description *
 */

public class SimpleApp3 {
    public static void main(String[] args) {
        // Should be some file on your system
        SparkConf conf = new SparkConf().setAppName("Simple Application").setMaster("local");
        SparkSession spark = SparkSession.builder().config(conf).getOrCreate();

        // 读取train.tsv
        System.out.println("开始导入数据...");
        Dataset<Row> rawDataWithHeader = spark.read().text("src\\main\\resources\\data\\train.tsv").cache();
        // 取第一项数据
        Row header = rawDataWithHeader.first();
        // 剔除字段名（特征名）行，取数据行
        Dataset<Row> rawData = rawDataWithHeader.filter((FilterFunction<Row>) row -> {
            System.out.println("row = " + row);
            return !row.equals(header);
        });
        rawData.show();
        spark.stop();
    }
}