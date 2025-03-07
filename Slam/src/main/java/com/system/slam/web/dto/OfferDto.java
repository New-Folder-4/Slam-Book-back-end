package com.system.slam.web.dto;

import java.time.LocalDateTime;
import java.util.List;

public class OfferDto {
    private Long idOfferList;
    private Long userId;
    private Long bookLiteraryId;
    private String isbn;
    private LocalDateTime yearPublishing;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private String status;
    private List<Long> categoryIds;

    public OfferDto() {}

    public OfferDto(Long idOfferList,
                    Long userId,
                    Long bookLiteraryId,
                    String isbn,
                    LocalDateTime yearPublishing,
                    LocalDateTime createAt,
                    LocalDateTime updateAt,
                    String status,
                    List<Long> categoryIds) {
        this.idOfferList = idOfferList;
        this.userId = userId;
        this.bookLiteraryId = bookLiteraryId;
        this.isbn = isbn;
        this.yearPublishing = yearPublishing;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.status = status;
        this.categoryIds = categoryIds;
    }

    public Long getIdOfferList() {
        return idOfferList;
    }

    public void setIdOfferList(Long idOfferList) {
        this.idOfferList = idOfferList;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBookLiteraryId() {
        return bookLiteraryId;
    }

    public void setBookLiteraryId(Long bookLiteraryId) {
        this.bookLiteraryId = bookLiteraryId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Long> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<Long> categoryIds) {
        this.categoryIds = categoryIds;
    }
}

