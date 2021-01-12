package com.banvien.kafka.external.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author sang.le-hoang on Jan 12, 2021
 */
@Component
public class LibraryConsumer extends Consumer {
    Logger logger = LoggerFactory.getLogger(LibraryConsumer.class);

    @Override
    @KafkaListener(topics = {"library-events"})
    public void onMessage(ConsumerRecord<Integer, String> record){
        logger.info("ConsumerRecord: {}", record);
    }
}
