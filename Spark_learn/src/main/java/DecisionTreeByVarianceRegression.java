import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaDoubleRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.mllib.tree.DecisionTree;
import org.apache.spark.mllib.util.MLUtils;
import org.apache.spark.sql.SparkSession;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @author LanceDai
 * @date 2019/1/31 10:51
 * @description 决策树（使用variance）进行回归
 */

public class DecisionTreeByVarianceRegression implements Serializable {
    public static void main(String[] args) {
        // Should be some file on your system 本地四线程
        SparkConf conf = new SparkConf().setAppName("Simple Application").setMaster("local[4]");
        SparkSession spark = SparkSession.builder().config(conf).getOrCreate();

        // 读取数据
        var data = MLUtils.loadLibSVMFile(spark.sparkContext(), ".\\src\\main\\resources\\data\\regression\\sample_libsvm_data.txt");
        // Split the data into training and test sets (30% held out for testing), 随机种子520L
        var splits = data.randomSplit(new double[]{0.7, 0.3}, 520L);
        var trainData = splits[0];
        var testData = splits[1];
        // Train a DecisionTree model.
        //  Empty categoricalFeaturesInfo indicates all features are continuous.
        var categoricalFeaturesInfo = new HashMap<Integer, Integer>();
        System.out.println("categoricalFeaturesInfo = " + categoricalFeaturesInfo.getClass().getName());
        var impurity = "variance";
        var maxDepth = 5;
        var maxBins = 32;
        var model = DecisionTree.trainRegressor(trainData.toJavaRDD(), categoricalFeaturesInfo, impurity, maxDepth, maxBins);
        // Evaluate model on test instances and compute test error
        var labelsAndPredictions = testData.toJavaRDD().map((Function<LabeledPoint, ArrayList<Double>>) point -> new ArrayList<>(Arrays.asList(point.label(), model.predict(point.features()))));
//        new Function1<LabeledPoint, ArrayList<Double>>() {
//            @Override
//            public ArrayList<Double> apply(LabeledPoint point) {
//                return new ArrayList<>(Arrays.asList(point.label(), model.predict(point.features())));
//            }
//        }, ClassTag.apply(ArrayList.class);
        System.out.println("data.count:" + data.count());
        System.out.println("trainingData.count:" + trainData.count());
        System.out.println("testData.count:" + testData.count());
        System.out.println("model.depth:" + model.depth());
        System.out.println("model.numNodes:" + model.numNodes());
        System.out.println("model.topNode:" + model.topNode());


        System.out.println("labelAndPreds");
        System.out.println("labelsAndPredictions.take(10) = " + labelsAndPredictions.take(10));

        var testMSE = labelsAndPredictions.map(list -> (Object) Math.pow((list.get(0) - list.get(1)), 2));
        Double MSE = new JavaDoubleRDD(testMSE.rdd()).mean();
        System.out.println("Test Mean Squared Error = " + MSE);
        System.out.println("Learned regression tree model:\n" + model.toDebugString());

        spark.stop();
    }
}
