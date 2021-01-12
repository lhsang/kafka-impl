package com.banvien.kafka.external.producer;

import com.banvien.kafka.dto.LibraryEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author sang.le-hoang on Jan 12, 2021
 */
@Component
public class LibraryEventProducer extends Producer<LibraryEvent>{
    Logger logger = LoggerFactory.getLogger(LibraryEventProducer.class);
    final private String TOPIC = "library-events";

    public LibraryEventProducer() {
        super();
        super.setTOPIC(TOPIC);
    }

    // SEND WITH CONTEXT TOPIC
    @Override
    public void sendEventSynchronous(Integer key, LibraryEvent libraryEvent) throws JsonProcessingException, ExecutionException, InterruptedException, TimeoutException {
        String value = objectMapper.writeValueAsString(libraryEvent);

        ProducerRecord<Integer, String> producerRecord = this.buildProducerRecord(key, value);

        ListenableFuture<SendResult<Integer, String>> sendResultListenableFuture = kafkaTemplate.send(producerRecord);

        // wait for response
        SendResult<Integer, String> sendResult = sendResultListenableFuture.get(3, TimeUnit.SECONDS);

        // you can add callback func by
        // sendResultListenableFuture.addCallback();

        logger.info("Message Sent to Topic #{} SuccessFully for the key : {} and the value is {} , partition is {}", this.TOPIC, key, value, sendResult.getRecordMetadata().partition());
    }

}
