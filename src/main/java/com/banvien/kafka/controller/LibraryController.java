package com.banvien.kafka.controller;

import com.banvien.kafka.dto.Library;
import com.banvien.kafka.service.LibraryService;
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
public class LibraryController {
    @Autowired
    LibraryService libraryService;

    @PostMapping
    public ResponseEntity<Library> saveLibrary(@RequestBody Library library) throws InterruptedException, ExecutionException, TimeoutException, JsonProcessingException {
        libraryService.save(library);
        return ResponseEntity.ok(library);
    }
}
