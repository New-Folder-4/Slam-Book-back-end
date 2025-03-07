package com.system.slam.web.dto;

public class AuthorDto {
    private Long idAuthor;
    private String firstName;
    private String lastName;

    public AuthorDto() {}

    public AuthorDto(Long idAuthor,
                     String firstName,
                     String lastName) {
        this.idAuthor = idAuthor;
        this.firstName = firstName;
        this.lastName = lastName;
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
