package com.system.slam.web.dto;

import java.time.LocalDateTime;

public class BookInfoDto {
    private String isbn;
    private String author;
    private String bookName;
    private LocalDateTime yearPublishing;
    private String coverUrl;

    public BookInfoDto() {}

    public BookInfoDto(String isbn,
                       String author,
                       String bookName,
                       LocalDateTime yearPublishing,
                       String coverUrl) {
        this.isbn = isbn;
        this.author = author;
        this.bookName = bookName;
        this.yearPublishing = yearPublishing;
        this.coverUrl = coverUrl;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public LocalDateTime getYearPublishing() {
        return yearPublishing;
    }

    public void setYearPublishing(LocalDateTime yearPublishing) {
        this.yearPublishing = yearPublishing;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }
}

