package com.learn.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.learn.bean.Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

/**
 * @author LanceDai
 * @date 2018/11/22 15:19
 * @description *
 */
@Component
@Slf4j
public class Producer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public Producer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, String msg) {
        log.info("向kafka推送数据:[{}]", msg);
        try {
            kafkaTemplate.send(topic, msg);
        } catch (Exception e) {
            log.error("发送数据出错！！！{}{}", topic, msg);
            log.error("发送数据出错=====>", e);
        }

        //消息发送的监听器，用于回调返回信息
        kafkaTemplate.setProducerListener(new ProducerListener<String, String>() {
            @Override
            public void onSuccess(String topic, Integer partition, String key, String value, RecordMetadata recordMetadata) {
                log.info("数据发送完毕");
            }

            @Override
            public void onError(String topic, Integer partition, String key, String value, Exception exception) {
                log.info("数据发送失败");
            }
        });
    }

    private Gson gson = new GsonBuilder().create();

    //发送消息方法
    public void send() {
        Message message = new Message();
        message.setId(System.currentTimeMillis());
        message.setMsg(UUID.randomUUID().toString());
        message.setSendTime(new Date());
        log.info("+++++++++++++++++++++  message = {}", gson.toJson(message));
        //topic-ideal为主题
        kafkaTemplate.send("topic-ideal", gson.toJson(message));
    }
}
