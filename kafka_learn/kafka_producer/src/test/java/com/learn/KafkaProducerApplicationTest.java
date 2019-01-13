package com.learn;

import com.learn.utils.Producer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
    @Test
    public void contextLoads() {
        this.kafkaProducer.send();
    }
}
