package com.system.slam.web.dto;

public class ReceiveRequest {
    private Long exchangeId;
    private Long userId;

    public ReceiveRequest() {
    }

    public ReceiveRequest(Long exchangeId,
                          Long userId) {
        this.exchangeId = exchangeId;
        this.userId = userId;
    }

    public Long getExchangeId() {
        return exchangeId;
    }

    public void setExchangeId(Long exchangeId) {
        this.exchangeId = exchangeId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
