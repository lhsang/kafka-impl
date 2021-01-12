package com.banvien.kafka.external.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;

/**
 * @author sang.le-hoang on Jan 12, 2021
 */
public abstract class Consumer {
    public Consumer() {
    }

    public void onMessage(ConsumerRecord<Integer, String> record){
        throw new RuntimeException("Method must be implemented");
    }

}
