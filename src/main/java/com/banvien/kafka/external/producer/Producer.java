package com.banvien.kafka.external.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * @author sang.le-hoang on Jan 12, 2021
 */
public abstract class Producer<T> {
    String TOPIC = null;

    @Autowired
    KafkaTemplate<Integer,String> kafkaTemplate;

    @Autowired
    ObjectMapper objectMapper;

    public Producer(String TOPIC) {
        this.TOPIC = TOPIC;
    }

    public void sendEventSynchronous(Integer key, T value) throws Exception {
        throw new Exception("Method need override");
    }

    public void sendEventAsynchronous(Integer key, T value) throws Exception {
        throw new Exception("Method need override");
    }

    public void sendEventSynchronous(T value) throws Exception {
        sendEventSynchronous(null, value);
    }

    public void sendEventAsynchronous(T value) throws Exception {
        sendEventAsynchronous(null, value);
    }

    public String getTOPIC() {
        return TOPIC;
    }

    public void setTOPIC(String TOPIC) {
        this.TOPIC = TOPIC;
    }
}
