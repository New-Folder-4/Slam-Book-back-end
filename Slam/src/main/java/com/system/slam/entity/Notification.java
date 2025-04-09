package com.system.slam.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Notification")
public class Notification {

    @Id
    @Column(name = "id_notification", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idNotification;

    @Column(name = "id_user")
    private Long idUser;

    @Column(name = "book", length = 50)
    private String book;

    @Column(name = "email", length = 254, nullable = false)
    private String email;

    public Notification() {

    }


    public Notification(Long idNotification, String book, String email) {
        this.idNotification = idNotification;
        this.book = book;
        this.email = email;
    }

    public Long getIdNotification() {
        return idNotification;
    }

    public void setIdNotification(Long idNotification) {
        this.idNotification = idNotification;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Notification that)) return false;
        return Objects.equals(idNotification, that.idNotification) && Objects.equals(idUser, that.idUser) && Objects.equals(book, that.book) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idNotification, idUser, book, email);
    }

    @Override
    public String toString() {
        return "Notification{" +
                "idNotification=" + idNotification +
                ", id_user=" + idUser +
                ", book='" + book + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
