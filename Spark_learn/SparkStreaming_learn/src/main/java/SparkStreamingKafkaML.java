import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.mllib.regression.StreamingLinearRegressionWithSGD;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.StreamingContext;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka010.ConsumerStrategies;
import org.apache.spark.streaming.kafka010.KafkaUtils;
import org.apache.spark.streaming.kafka010.LocationStrategies;
import scala.Tuple2;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


/**
 * @author LanceDai
 * @date 2019/2/7 09:31
 * @description Creating a Direct Stream
 */
@Slf4j
public class SparkStreamingKafkaML {
    public static void main(String[] args) throws InterruptedException {

        var conf = new SparkConf().setAppName("SparkStreamingKafkaSimpleApp").setMaster("local[4]");
        var ssc = new StreamingContext(conf, Duration.apply(5000L));
        var javaStreamingContext = new JavaStreamingContext(ssc);

        Map<String, Object> kafkaParams = new HashMap<>();
        kafkaParams.put("bootstrap.servers", "192.168.136.21:9092,192.168.136.22:9092,192.168.136.23:9092");
        kafkaParams.put("key.deserializer", StringDeserializer.class);
        kafkaParams.put("value.deserializer", StringDeserializer.class);
        kafkaParams.put("group.id", "spark-consumer");
        kafkaParams.put("auto.offset.reset", "latest");
        kafkaParams.put("enable.auto.commit", false);

        Collection<String> topics = Collections.singletonList("spark-streaming-kafka");

        JavaInputDStream<ConsumerRecord<Object, Object>> stream =
                KafkaUtils.createDirectStream(
                        javaStreamingContext,
                        LocationStrategies.PreferConsistent(),
                        ConsumerStrategies.Subscribe(topics, kafkaParams)
                );

        stream.mapToPair(record -> new Tuple2<>(record.key(), record.value()));
        log.info("创建在线学习");
        //创建一个含标签的数据流
        JavaDStream<LabeledPoint> labeledStream = stream.map((Function<ConsumerRecord<Object, Object>, LabeledPoint>) consumerRecord -> {
            //把kafka中的数据转变成训练集
            String msg = (String) consumerRecord.value();
            log.info("sparkStreaming kafka msg : " + msg);
            //得到的数据是用户ID， 问题ID， 操作
            //比如 id为100的用户浏览了id为1000的问题
            //以json字段传输
            log.info(msg);
            JSONObject jsonObject = JSONObject.parseObject(msg);
            Long userId = jsonObject.getLong("userId");
            Long questionId = jsonObject.getLong("questionId");
            //操作码 1代表关注， 2代表浏览
            int operationId = jsonObject.getIntValue("operationId");
            //构造数据集
//            Vector features = getFeatures(userId, questionId);
            Vector features = Vectors.dense(1.0, 1.0, 1.0);
            double weight;
            switch (operationId) {
                case 1:
                    weight = 2;
                    break;
                case 2:
                    weight = 1;
                    break;
                default:
                    weight = 0;
                    break;
            }
            //更新权重
//            updateWeight(userId, questionId, weight);
            return LabeledPoint.apply(weight, features);
        });

        long numFeatures = 3;
        double[] zeros = new double[(int) numFeatures];
        StreamingLinearRegressionWithSGD model = new StreamingLinearRegressionWithSGD()
                .setInitialWeights(Vectors.dense(zeros))
                .setNumIterations(20)
                .setRegParam(0.8)
                .setStepSize(0.01);

        //在数据流上训练测试模型。
        model.trainOn(labeledStream);
        model.predictOnValues(
                labeledStream.mapToPair(
                        (PairFunction<LabeledPoint, Double, Vector>)
                                labeledPoint ->
                                        Tuple2.apply(labeledPoint.label(),
                                                labeledPoint.features()))).print();
        //启动Spark Streaming
        log.info("启动Spark Streaming");
        javaStreamingContext.start();
        javaStreamingContext.awaitTermination();
    }
}
