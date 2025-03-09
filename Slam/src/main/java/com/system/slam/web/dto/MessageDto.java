package com.system.slam.web.dto;

import java.time.LocalDateTime;

public class MessageDto {
    private Long id;
    private Long userId;
    private String text;
    private LocalDateTime createAt;

    public MessageDto() {}

    public MessageDto(Long id, Long userId,
                      String text,
                      LocalDateTime createAt) {
        this.id = id;
        this.userId = userId;
        this.text = text;
        this.createAt = createAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }
}

