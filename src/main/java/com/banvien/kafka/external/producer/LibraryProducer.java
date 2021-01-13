package com.banvien.kafka.external.producer;

import com.banvien.kafka.Constant.KafkaProducerConstant;
import com.banvien.kafka.dto.Library;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author sang.le-hoang on Jan 12, 2021
 */
@Component
public class LibraryProducer extends Producer<Library>{
    Logger logger = LoggerFactory.getLogger(LibraryProducer.class);
    final private String TOPIC = "library-events";

    public LibraryProducer() {
        super();
        super.setTOPIC(TOPIC);
    }

    @Override
    public void sendEventSynchronous(Integer key, Library library) throws JsonProcessingException, ExecutionException, InterruptedException, TimeoutException {
        String value = objectMapper.writeValueAsString(library);

        ProducerRecord<Integer, String> producerRecord = this.buildProducerRecord(key, value);

        ListenableFuture<SendResult<Integer, String>> sendResultListenableFuture = kafkaTemplate.send(producerRecord);

        // wait for response
        SendResult<Integer, String> sendResult = sendResultListenableFuture.get(KafkaProducerConstant.TIME_OUT_4_RESPONSE, TimeUnit.SECONDS);

        logger.info("Message sent to Topic #{} SuccessFully for the key: {} and the value is {} , partition is {}", this.TOPIC, key, value, sendResult.getRecordMetadata().partition());
    }

    @Override
    public void sendEventAsynchronous(Integer key, Library library) throws JsonProcessingException {
        String value = objectMapper.writeValueAsString(library);

        ProducerRecord<Integer, String> producerRecord = this.buildProducerRecord(key, value);

        ListenableFuture<SendResult<Integer, String>> sendResultListenableFuture = kafkaTemplate.send(producerRecord);

        sendResultListenableFuture.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() {
            @Override
            public void onFailure(Throwable ex) {
                logger.error("Error Sending the Message and the exception is {}", ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<Integer, String> result) {
                logger.info("Message sent successFully for the key: {} and the value is {} , partition is {}", key, value, result.getRecordMetadata().partition());
            }
        });
    }

}
