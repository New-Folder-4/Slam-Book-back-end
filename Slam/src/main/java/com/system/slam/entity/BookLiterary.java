package com.system.slam.entity;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "BookLiterary")
public class BookLiterary {

    @Id
    @Column(name = "IdBookLiterary", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBookLiterary;

    @ManyToOne
    @JoinColumn(name = "IdAutor", nullable = false)
    private Autor autor;

    @Column(name = "BookName", length = 50, nullable = false)
    private String bookName;

    @Column(name = "Note", length = 50)
    private String note;

    public BookLiterary() {}

    public BookLiterary(Long idBookLiterary,
                        Autor autor,
                        String bookName,
                        String note) {
        this.idBookLiterary = idBookLiterary;
        this.autor = autor;
        this.bookName = bookName;
        this.note = note;
    }

    public Long getIdBookLiterary() {
        return idBookLiterary;
    }

    public void setIdBookLiterary(Long idBookLiterary) {
        this.idBookLiterary = idBookLiterary;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
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
                Objects.equals(autor, that.autor) &&
                Objects.equals(bookName, that.bookName) &&
                Objects.equals(note, that.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBookLiterary, autor, bookName, note);
    }

    @Override
    public String toString() {
        return "BookLiterary{" +
                "idBookLiterary=" + idBookLiterary +
                ", autor=" + autor +
                ", bookName='" + bookName + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
