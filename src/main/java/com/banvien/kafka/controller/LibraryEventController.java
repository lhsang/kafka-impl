package com.banvien.kafka.controller;

import com.banvien.kafka.dto.LibraryEvent;
import com.banvien.kafka.external.producer.LibraryEventProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * @author sang.le-hoang on Jan 12, 2021
 */
@RestController
@RequestMapping("/libraryevent")
public class LibraryEventController {
    @Autowired
    LibraryEventProducer libraryEventProducer;

    @PostMapping("")
    public ResponseEntity<LibraryEvent> createLibraryEvent(@RequestBody LibraryEvent libraryEvent) throws InterruptedException, ExecutionException, TimeoutException, JsonProcessingException {
        libraryEventProducer.sendEvent(libraryEvent.getLibraryEventId(), libraryEvent);
        return ResponseEntity.ok(libraryEvent);
    }
}
