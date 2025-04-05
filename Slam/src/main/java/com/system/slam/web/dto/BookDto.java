package com.system.slam.web.dto;

public class BookDto {
    private Long idBookLiterary;
    private Long idAuthor;
    private String bookName;
    private String note;

    public BookDto() {
    }

    public BookDto(Long idBookLiterary,
                   Long idAuthor,
                   String bookName,
                   String note) {
        this.idBookLiterary = idBookLiterary;
        this.idAuthor = idAuthor;
        this.bookName = bookName;
        this.note = note;
    }

    public Long getIdBookLiterary() {
        return idBookLiterary;
    }

    public void setIdBookLiterary(Long idBookLiterary) {
        this.idBookLiterary = idBookLiterary;
    }

    public Long getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(Long idAuthor) {
        this.idAuthor = idAuthor;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}

