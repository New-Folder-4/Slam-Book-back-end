package com.system.slam.entity;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "BookLiterary")
public class BookLiterary {

    @Id
    @Column(name = "IdBookLiterary", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idBookLiterary;

    @ManyToOne
    @JoinColumn(name = "IdAuthor", nullable = false)
    private Author author;

    @Column(name = "BookName", length = 50, nullable = false)
    private String bookName;

    @Column(name = "Note", length = 50)
    private String note;

    public BookLiterary() {}

    public BookLiterary(Long idBookLiterary,
                        Author author,
                        String bookName,
                        String note) {
        this.idBookLiterary = idBookLiterary;
        this.author = author;
        this.bookName = bookName;
        this.note = note;
    }

    public Long getIdBookLiterary() {
        return idBookLiterary;
    }

    public void setIdBookLiterary(Long idBookLiterary) {
        this.idBookLiterary = idBookLiterary;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
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

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        BookLiterary that = (BookLiterary) object;
        return Objects.equals(idBookLiterary, that.idBookLiterary) &&
                Objects.equals(author, that.author) &&
                Objects.equals(bookName, that.bookName) &&
                Objects.equals(note, that.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBookLiterary, author, bookName, note);
    }

    @Override
    public String toString() {
        return "BookLiterary{" +
                "idBookLiterary=" + idBookLiterary +
                ", author=" + author +
                ", bookName='" + bookName + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
