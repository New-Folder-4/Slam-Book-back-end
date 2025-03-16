package com.system.slam.web.dto;

public class ProposeRequest {
    private Long offerId;
    private Long wishId;

    public ProposeRequest() {
    }

    public ProposeRequest(Long offerId,
                          Long wishId) {
        this.offerId = offerId;
        this.wishId = wishId;
    }

    public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }

    public Long getWishId() {
        return wishId;
    }

    public void setWishId(Long wishId) {
        this.wishId = wishId;
    }
}
