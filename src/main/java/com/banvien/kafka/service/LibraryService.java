package com.banvien.kafka.service;

import com.banvien.kafka.dto.Library;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * @author sang.le-hoang on Jan 12, 2021
 */
public interface LibraryService {
   void save(Library library) throws InterruptedException, ExecutionException, TimeoutException, JsonProcessingException;
}
