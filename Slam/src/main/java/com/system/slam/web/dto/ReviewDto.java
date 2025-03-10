package com.system.slam.web.dto;

import java.time.LocalDateTime;

public class ReviewDto {
    private Long idBookResponse;
    private Long bookLiteraryId;
    private Long userId;
    private LocalDateTime createAt;
    private String response;

    public ReviewDto() {}

    public ReviewDto(Long idBookResponse,
                     Long bookLiteraryId,
                     Long userId,
                     LocalDateTime createAt,
                     String response) {
        this.idBookResponse = idBookResponse;
        this.bookLiteraryId = bookLiteraryId;
        this.userId = userId;
        this.createAt = createAt;
        this.response = response;
    }

    public Long getIdBookResponse() {
        return idBookResponse;
    }

    public void setIdBookResponse(Long idBookResponse) {
        this.idBookResponse = idBookResponse;
    }

    public Long getBookLiteraryId() {
        return bookLiteraryId;
    }

    public void setBookLiteraryId(Long bookLiteraryId) {
        this.bookLiteraryId = bookLiteraryId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
}

