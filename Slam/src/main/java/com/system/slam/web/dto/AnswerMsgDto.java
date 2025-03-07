package com.system.slam.web.dto;

public class AnswerMsgDto {
    private Long messageId;
    private String text;

    public AnswerMsgDto() {
    }

    public AnswerMsgDto(Long messageId, String text) {
        this.messageId = messageId;
        this.text = text;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
