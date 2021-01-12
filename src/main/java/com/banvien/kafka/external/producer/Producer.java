package com.banvien.kafka.external.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * @author sang.le-hoang on Jan 12, 2021
 */
public abstract class Producer<T> {
    private String TOPIC = null;

    @Autowired
    KafkaTemplate<Integer,String> kafkaTemplate;

    @Autowired
    ObjectMapper objectMapper;

    public Producer() {}

    public void sendEventSynchronous(Integer key, T value) throws RuntimeException, JsonProcessingException, ExecutionException, InterruptedException, TimeoutException {
        throw new RuntimeException("Method need override");
    }

    public void sendEventSynchronous(T value) throws Exception {
        sendEventSynchronous(null, value);
    }

    public void sendEventAsynchronous(Integer key, T value) throws RuntimeException {
        // you can add callback func by
        // sendResultListenableFuture.addCallback();

        throw new RuntimeException("Method need override");
    }

    public void sendEventAsynchronous(T value) throws Exception {
        sendEventAsynchronous(null, value);
    }

    public ProducerRecord<Integer, String> buildProducerRecord(String topic, Integer key, String value){
        return new ProducerRecord<>(topic, null, key, value, null);
    }

    public ProducerRecord<Integer, String> buildProducerRecord(Integer key, String value){
        return buildProducerRecord(this.TOPIC, key, value);
    }

    public String getTOPIC() {
        return TOPIC;
    }

    public void setTOPIC(String TOPIC) {
        this.TOPIC = TOPIC;
    }
}
