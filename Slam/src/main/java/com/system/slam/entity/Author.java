package com.system.slam.entity;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Author")
public class Author {

    @Id
    @Column(name = "id_author", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idAuthor;

    @Column(name = "last_name", length = 20, nullable = false)
    private String lastName;

    @Column(name = "first_name", length = 20)
    private String firstName;

    public Author() {}

    public Author(Long idAuthor, String lastName, String firstName) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author)) return false;
        Author author = (Author) o;
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
        return "Author{" +
                "idAuthor=" + idAuthor +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                '}';
    }
}
