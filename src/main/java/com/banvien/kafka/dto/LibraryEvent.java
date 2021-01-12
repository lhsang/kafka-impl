package com.banvien.kafka.dto;

/**
 * @author sang.le-hoang on Jan 12, 2021
 */
public class LibraryEvent {
    private Integer libraryEventId;
    private BookDTO book;

    public Integer getLibraryEventId() {
        return libraryEventId;
    }

    public void setLibraryEventId(Integer libraryEventId) {
        this.libraryEventId = libraryEventId;
    }

    public BookDTO getBook() {
        return book;
    }

    public void setBook(BookDTO book) {
        this.book = book;
    }
}
