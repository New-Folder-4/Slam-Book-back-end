package com.system.slam.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "BookResponse")
public class BookResponse {

    @Id
    @Column(name = "IdBookResponse", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idBookResponse;

    @ManyToOne
    @JoinColumn(name = "IdBookLiterary", nullable = false)
    private BookLiterary bookLiterary;

    @ManyToOne
    @JoinColumn(name = "IdUser", nullable = false)
    private User user;

    @Column(name = "CreateAt", nullable = false)
    private LocalDateTime createAt;

    @Column(name = "Response", length = 500, nullable = false)
    private String response;

    @Column(name = "Note", length = 50)
    private String note;

    public BookResponse() {}

    public BookResponse(Long idBookResponse,
                        BookLiterary bookLiterary,
                        User user,
                        LocalDateTime createAt,
                        String response,
                        String note) {
        this.idBookResponse = idBookResponse;
        this.bookLiterary = bookLiterary;
        this.user = user;
        this.createAt = createAt;
        this.response = response;
        this.note = note;
    }

    public Long getIdBookResponse() {
        return idBookResponse;
    }

    public void setIdBookResponse(Long idBookResponse) {
        this.idBookResponse = idBookResponse;
    }

    public BookLiterary getBookLiterary() {
        return bookLiterary;
    }

    public void setBookLiterary(BookLiterary bookLiterary) {
        this.bookLiterary = bookLiterary;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        BookResponse that = (BookResponse) object;
        return Objects.equals(idBookResponse, that.idBookResponse) &&
                Objects.equals(bookLiterary, that.bookLiterary) &&
                Objects.equals(user, that.user) &&
                Objects.equals(createAt, that.createAt) &&
                Objects.equals(response, that.response) &&
                Objects.equals(note, that.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBookResponse, bookLiterary,
                user, createAt, response, note);
    }

    @Override
    public String toString() {
        return "BookResponse{" +
                "idBookResponse=" + idBookResponse +
                ", bookLiterary=" + bookLiterary +
                ", user=" + user +
                ", createAt=" + createAt +
                ", response='" + response + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
