package com.banvien.kafka.dto;

/**
 * @author sang.le-hoang on Jan 12, 2021
 */
public class Library {
    private String libraryId;
    private BookDTO book;

    public Library() {
    }

    public Library(String libraryId) {
        this.libraryId = libraryId;
    }

    public Library(BookDTO book) {
        this.book = book;
    }

    public Library(String libraryId, BookDTO book) {
        this.libraryId = libraryId;
        this.book = book;
    }

    public String getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(String libraryId) {
        this.libraryId = libraryId;
    }

    public BookDTO getBook() {
        return book;
    }

    public void setBook(BookDTO book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "Library{" +
                "libraryId='" + libraryId + '\'' +
                ", book=" + book +
                '}';
    }
}
