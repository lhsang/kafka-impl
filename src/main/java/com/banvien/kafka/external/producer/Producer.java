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
public abstract class Producer<K, V> {
    private String TOPIC = null;

    @Autowired
    KafkaTemplate<K,V> kafkaTemplate;

    @Autowired
    ObjectMapper objectMapper;

    public Producer() {}

    public void sendEventSynchronous(K key, Object value) throws RuntimeException, JsonProcessingException, ExecutionException, InterruptedException, TimeoutException {
        throw new RuntimeException("Method need override");
    }

    public void sendEventSynchronous(Object value) throws Exception {
        sendEventSynchronous(null, value);
    }

    public void sendEventAsynchronous(K key, Object value) throws RuntimeException, JsonProcessingException {
        // you can add callback func by
        // sendResultListenableFuture.addCallback();

        throw new RuntimeException("Method need override");
    }

    public void sendEventAsynchronous(V value) throws Exception {
        sendEventAsynchronous(null, value);
    }

    public ProducerRecord<K, V> buildProducerRecord(String topic, K key, V value){
        return new ProducerRecord<K, V>(topic, null, key, value, null);
    }

    public ProducerRecord<K, V> buildProducerRecord(K key, V value){
        return buildProducerRecord(this.TOPIC, key, value);
    }

    public String getTOPIC() {
        return TOPIC;
    }

    public void setTOPIC(String TOPIC) {
        this.TOPIC = TOPIC;
    }
}
