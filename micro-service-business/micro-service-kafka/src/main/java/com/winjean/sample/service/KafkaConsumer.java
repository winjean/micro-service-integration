package com.winjean.sample.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author ：winjean
 * @date ：Created in 2019/4/13 15:01
 * @description：${description}
 * @modified By：
 * @version: $version$
 */

@Component
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = "topic-1")
    public void listen (ConsumerRecord<?, ?> record) throws Exception {
        System.out.printf("topic = %s, offset = %d, value = %s \n", record.topic(), record.offset(), record.value());
    }
}
