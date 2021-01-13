package com.banvien.kafka.dto;

/**
 * @author sang.le-hoang on Jan 12, 2021
 */
public class Library {
    private Integer libraryId;
    private BookDTO book;

    public Integer getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(Integer libraryId) {
        this.libraryId = libraryId;
    }

    public BookDTO getBook() {
        return book;
    }

    public void setBook(BookDTO book) {
        this.book = book;
    }
}
