package com.system.slam.web.dto;

public class TrackNumberRequest {
    private Long exchangeId;
    private Long userId;
    private String trackNumber;

    public TrackNumberRequest() {
    }

    public TrackNumberRequest(Long exchangeId,
                              Long userId,
                              String trackNumber) {
        this.exchangeId = exchangeId;
        this.userId = userId;
        this.trackNumber = trackNumber;
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

    public String getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(String trackNumber) {
        this.trackNumber = trackNumber;
    }
}
