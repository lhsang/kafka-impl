package com.banvien.kafka.external.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.*;

/**
 * @author sang.le-hoang on Jan 12, 2021
 */
public abstract class Consumer {
    private static final ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, false);

    public Consumer() {
    }

    public void onMessage(ConsumerRecord<Integer, String> record){
        throw new RuntimeException("Method must be implemented");
    }

    public <T> T getPayloadAsMono(String rawPayload, Class<T> dtoType){
        try {
            return mapper.readValue(rawPayload, dtoType);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T> List<T> getPayloadAsList(Object rawPayload, Class<T> dtoType) {
        List<Map<String, Object>> body = mapper.convertValue(rawPayload, new TypeReference<List<Map<String, Object>>>() {});
        List<T> payload = new ArrayList<>();

        for (Map<String, Object> data: body) {
            payload.add(mapper.convertValue(data, dtoType));
        }

        return payload;
    }
}
