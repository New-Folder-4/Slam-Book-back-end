package com.system.slam.entity.list;

import com.system.slam.entity.BookLiterary;
import com.system.slam.entity.Status;
import com.system.slam.entity.User;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "OfferList")
public class OfferList {

    @Id
    @Column(name = "IdOfferList", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idOfferList;

    @ManyToOne
    @JoinColumn(name = "IdBookLiterary", nullable = false)
    private BookLiterary bookLiterary;

    @ManyToOne
    @JoinColumn(name = "IdUser", nullable = false)
    private User user;

    @Column(name = "ISBN", length = 13)
    private String isbn;

    @Column(name = "YearPublishing", nullable = false)
    private LocalDateTime yearPublishing;

    @Column(name = "CreateAt", nullable = false)
    private LocalDateTime createAt;

    @Column(name = "UpdateAt", nullable = false)
    private LocalDateTime updateAt;

    @ManyToOne
    @JoinColumn(name = "IdStatus", nullable = false)
    private Status status;

    public OfferList() {}

    public OfferList(Long idOfferList,
                     BookLiterary bookLiterary,
                     User user, String isbn,
                     LocalDateTime yearPublishing,
                     LocalDateTime createAt,
                     LocalDateTime updateAt,
                     Status status) {
        this.idOfferList = idOfferList;
        this.bookLiterary = bookLiterary;
        this.user = user;
        this.isbn = isbn;
        this.yearPublishing = yearPublishing;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.status = status;
    }

    public Long getIdOfferList() {
        return idOfferList;
    }

    public void setIdOfferList(Long idOfferList) {
        this.idOfferList = idOfferList;
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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public LocalDateTime getYearPublishing() {
        return yearPublishing;
    }

    public void setYearPublishing(LocalDateTime yearPublishing) {
        this.yearPublishing = yearPublishing;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        OfferList offerList = (OfferList) object;
        return Objects.equals(idOfferList, offerList.idOfferList) &&
                Objects.equals(bookLiterary, offerList.bookLiterary) &&
                Objects.equals(user, offerList.user) &&
                Objects.equals(isbn, offerList.isbn) &&
                Objects.equals(yearPublishing, offerList.yearPublishing) &&
                Objects.equals(createAt, offerList.createAt) &&
                Objects.equals(updateAt, offerList.updateAt) &&
                Objects.equals(status, offerList.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOfferList, bookLiterary, user, isbn,
                yearPublishing, createAt, updateAt, status);
    }

    @Override
    public String toString() {
        return "OfferList{" +
                "idOfferList=" + idOfferList +
                ", bookLiterary=" + bookLiterary +
                ", user=" + user +
                ", isbn='" + isbn + '\'' +
                ", yearPublishing=" + yearPublishing +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                ", status=" + status +
                '}';
    }
}