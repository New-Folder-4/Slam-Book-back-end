package com.system.slam.web.dto;

public class BookDto {
    private Long idBookLiterary;
    private Long idAuthor;
    private String firstName;
    private String lastName;

    public BookDto() {
    }

    public BookDto(Long idBookLiterary,
                   Long idAuthor,
                   String firstName,
                   String lastName) {
        this.idBookLiterary = idBookLiterary;
        this.idAuthor = idAuthor;
        this.firstName = firstName;
        this.lastName = lastName;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
