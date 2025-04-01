package com.system.slam.entity;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Author")
public class Author {

    @Id
    @Column(name = "idAuthor", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idAuthor;

    @Column(name = "LastName", length = 20, nullable = false)
    private String lastName;

    @Column(name = "FirstName", length = 20)
    private String firstName;

    public Author() {}

    public Author(Long idAuthor,
                  String lastName,
                  String firstName) {
        this.idAuthor = idAuthor;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public Long getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(Long idAuthor) {
        this.idAuthor = idAuthor;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Author author = (Author) object;
        return Objects.equals(idAuthor, author.idAuthor) &&
                Objects.equals(lastName, author.lastName) &&
                Objects.equals(firstName, author.firstName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAuthor, lastName, firstName);
    }

    @Override
    public String toString() {
        return "Autor{" +
                "idAutor=" + idAuthor +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                '}';
    }
}