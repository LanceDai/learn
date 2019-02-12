package com.learn;

import com.alibaba.fastjson.JSONObject;
import com.learn.config.KafkaConfig;
import com.learn.utils.Producer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

/**
 * @author LanceDai
 * @date 2018/11/22 15:37
 * @description *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaProducerApplicationTest {
    @Autowired
    Producer kafkaProducer;
    @Autowired
    KafkaConfig kafkaConfig;

    @Test
    public void contextLoads() {
        this.kafkaProducer.send();
    }

    @Test
    public void sparkStreamingTest() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            kafkaConfig.topics().forEach(topic -> {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("userId", 100);
                jsonObject.put("questionId", 1000);
                jsonObject.put("operationId", new Random().nextInt(1) + 1);
                assert jsonObject.toJSONString().equals(jsonObject.toJSONString());
                for (int j = 0; j < 100; j++) {
                    kafkaProducer.sendMessage(topic, jsonObject.toJSONString());
                }
            });
            Thread.sleep(1000);
        }
    }

}