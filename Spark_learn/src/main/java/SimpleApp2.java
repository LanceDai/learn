/* SimpleApp.java */

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.FilterFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.mllib.evaluation.BinaryClassificationMetrics;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.mllib.tree.DecisionTree;
import org.apache.spark.mllib.tree.model.DecisionTreeModel;
import org.apache.spark.sql.*;
import scala.Tuple2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author LanceDai
 * @date 2019/1/18 10:22
 * @description *
 */

public class SimpleApp2 {

    public static void main(String[] args) throws IOException {
        // Should be some file on your system
        SparkConf conf = new SparkConf().setAppName("Simple Application").setMaster("local");
        SparkSession spark = SparkSession.builder().config(conf).getOrCreate();
        //读取train.tsv
        System.out.println("开始导入数据...");
        Dataset<Row> rawDataWithHeader = spark.read().text("src\\main\\resources\\data\\train.tsv");
        //取出前2项数据
        List<Row> test = rawDataWithHeader.takeAsList(2);
//        System.out.println("test = " + test);

        // 读取train.tsv
        System.out.println("开始导入数据...");
        rawDataWithHeader = spark.read().text("src\\main\\resources\\data\\train.tsv").cache();
        // 取第一项数据
        Row header = rawDataWithHeader.first();
        System.out.println("header = " + header);
        // 剔除字段名（特征名）行，取数据行
        Dataset<Row> rawData = rawDataWithHeader.filter((FilterFunction<Row>) row -> !row.equals(header));
        // 将双引号"替换为空字符（剔除双引号）
        Dataset<String> rData = rawData.map((MapFunction<Row, String>) row -> {
            StringBuilder string = new StringBuilder();
            for (int i = 0; i < header.size(); i++) {
                string.append(row.getString(i).replace("\"", " "));
            }
            return string.toString();
        }, Encoders.STRING());
        System.out.println("rData.count() = " + rData.count());
        // 以制表符分割每一行
        Dataset<Row> lines = rData.map((MapFunction<String, Row>) s ->
                RowFactory.create((Object[]) s.split("\t")), Encoders.kryo(Row.class));
        System.out.println("共有：" + lines.count() + "项数据");
        Map<String, Long> stringLongMap = lines.map((MapFunction<Row, String>) row ->
                row.getString(3), Encoders.STRING()).distinct().javaRDD().zipWithIndex().collectAsMap();
        Dataset<LabeledPoint> labelpointRDD = lines.map((MapFunction<Row, LabeledPoint>) row ->
                (LabeledPoint) LabeledPoint.apply(Double.valueOf(row.getString(row.size() - 1).trim()), processFeatures(row, stringLongMap, 25)), Encoders.kryo(LabeledPoint.class));
        System.out.println("labelpointRDD.takeAsList(1) = " + labelpointRDD.takeAsList(1));
        System.out.println("labelpointRDD.takeAsList(1).size() = " + labelpointRDD.takeAsList(1).size());
        //划分训练集、验证集以及测试集
        Dataset<LabeledPoint>[] datas = labelpointRDD.randomSplit(new double[]{7, 1, 2});
        Dataset<LabeledPoint> trainData = datas[0];
        Dataset<LabeledPoint> validationData = datas[1];
        Dataset<LabeledPoint> testData = datas[2];
        System.out.println("训练集样本个数：" + trainData.count() + "验证集样本个数：" + validationData.count() + "测试集样本个数：" + testData.count());
        //暂存内存加快速度
        trainData.persist();
        validationData.persist();
        testData.persist();
        Map<Integer, Integer> map = new HashMap<>();
        //训练模型
        DecisionTreeModel model = DecisionTree.trainClassifier(trainData.javaRDD(), 2, map, "entropy", 5, 5);

        Dataset<Double> predict = validationData.map((MapFunction<LabeledPoint, Double>)
                labeledPoint -> {
            Vector vector = labeledPoint.features();
            return model.predict(vector);
        }, Encoders.DOUBLE());
        var predictReal = predict.javaRDD().zip(validationData.map(new MapFunction<LabeledPoint, Double>() {
            @Override
            public Double call(LabeledPoint labeledPoint) throws Exception {

                return labeledPoint.label();
            }
        }, Encoders.DOUBLE()).javaRDD());
        System.out.println("predict_real.take(5) = " + predictReal.take(5));
        var newPredictReal = predictReal.map((Function<Tuple2<Double, Double>, Tuple2<Object, Object>>) doubleDoubleTuple2 -> Tuple2.apply(doubleDoubleTuple2._1, doubleDoubleTuple2._2));
        BinaryClassificationMetrics binaryClassificationMetrics = new BinaryClassificationMetrics(newPredictReal.rdd());
        System.out.println("binaryClassificationMetrics.areaUnderROC() = " + binaryClassificationMetrics.areaUnderROC());
        System.out.println("model.toDebugString() = " + model.toDebugString());
        spark.stop();
    }

    /**
     * 数据预处理 独热编码
     */
    private static Vector processFeatures(Row line, Map<String, Long> categoriesMap, int featureEnd) {
        //处理特征，line为字段行，categoriesMap为网页分类字典，featureEnd为特征结束位置，此例为25
        //处理alchemy_category网页分类特征
        Long categoryIdx = categoriesMap.get(line.getString(3));
        List<Double> oneHot = new ArrayList<>();
        for (int i = 0; i < categoriesMap.size(); ++i) {
            oneHot.add((double) 0);
        }
        oneHot.set(Math.toIntExact(categoryIdx), 1.0);
        double[] res = new double[oneHot.size() + featureEnd - 4 + 1];
        AtomicInteger count = new AtomicInteger();
        oneHot.forEach(val -> {
            res[count.getAndIncrement()] = val;
        });
        //处理数值特征
        for (int i = 4; i <= featureEnd; i++) {
            res[count.getAndIncrement()] = convert(line.getString(i));
        }
        return Vectors.dense(res);
    }

    private static double convert(String val) {
        return "?".equals(val.trim()) ? 0f : Double.valueOf(val.trim());
    }

}