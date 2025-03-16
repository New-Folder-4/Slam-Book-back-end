package com.system.slam.web.dto;

public class ConfirmRequest {
    private Long secondOfferId;
    private Long secondWishId;

    public ConfirmRequest() {
    }

    public ConfirmRequest(Long secondOfferId, Long secondWishId) {
        this.secondOfferId = secondOfferId;
        this.secondWishId = secondWishId;
    }

    public Long getSecondOfferId() {
        return secondOfferId;
    }

    public void setSecondOfferId(Long secondOfferId) {
        this.secondOfferId = secondOfferId;
    }

    public Long getSecondWishId() {
        return secondWishId;
    }

    public void setSecondWishId(Long secondWishId) {
        this.secondWishId = secondWishId;
    }
}
