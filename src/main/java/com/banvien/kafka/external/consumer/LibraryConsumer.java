package com.banvien.kafka.external.consumer;

import com.banvien.kafka.constant.KafkaConsumerConstant;
import com.banvien.kafka.dto.Library;
import com.banvien.kafka.service.LibraryService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author sang.le-hoang on Jan 12, 2021
 */
@Component
public class LibraryConsumer extends Consumer {
    Logger logger = LoggerFactory.getLogger(LibraryConsumer.class);

    @Autowired
    LibraryService libraryService;

    @Override
    @KafkaListener(topics = {KafkaConsumerConstant.TopicListener.LIBRARY_EVENT_TOPIC})
    public void onMessage(ConsumerRecord<Integer, String> record){
        logger.info("ConsumerRecord: {}", record);

        // convert to dto
        Library library = this.getPayloadAsMono(record.value(), Library.class);

        // re-handle
         libraryService.onSavedLibrary(library);
    }
}
