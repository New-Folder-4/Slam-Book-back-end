package com.system.slam.entity;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Autor")
public class Autor {

    @Id
    @Column(name = "IdAutor", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAutor;

    @Column(name = "LastName", length = 20, nullable = false)
    private String lastName;

    @Column(name = "FirstName", length = 20)
    private String firstName;

    public Autor() {}

    public Autor(Long idAutor,
                 String lastName,
                 String firstName) {
        this.idAutor = idAutor;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public Long getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(Long idAutor) {
        this.idAutor = idAutor;
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
        Autor autor = (Autor) object;
        return Objects.equals(idAutor, autor.idAutor) &&
                Objects.equals(lastName, autor.lastName) &&
                Objects.equals(firstName, autor.firstName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAutor, lastName, firstName);
    }

    @Override
    public String toString() {
        return "Autor{" +
                "idAutor=" + idAutor +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                '}';
    }
}