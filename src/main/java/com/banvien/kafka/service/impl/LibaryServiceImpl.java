package com.banvien.kafka.service.impl;

import com.banvien.kafka.dto.Library;
import com.banvien.kafka.external.producer.LibraryProducer;
import com.banvien.kafka.service.LibraryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * @author sang.le-hoang on Jan 12, 2021
 */
@Service
public class LibaryServiceImpl implements LibraryService {
    Logger logger = LoggerFactory.getLogger(LibaryServiceImpl.class);

    @Autowired
    LibraryProducer libraryProducer;

    @Override
    public void save(Library libraryEvent) throws InterruptedException, ExecutionException, TimeoutException, JsonProcessingException {
        // handle biz logic

        // call to producer to send
        libraryProducer.sendEventSynchronous(libraryEvent.getLibraryId(), libraryEvent);
    }

    @Override
    public void onSavedLibrary(Library library) {
        logger.info("Handle biz logic after event");
        logger.info("Received: {}", library.toString());
    }

}
