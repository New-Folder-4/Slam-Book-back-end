package com.system.slam.web.dto;

import java.time.LocalDateTime;

public class ExchangeDto {
    private Long exchangeId;
    private Long offerId;
    private Long wishId;
    private Boolean isBoth;
    private LocalDateTime createAt;

    public ExchangeDto() {}

    public ExchangeDto(Long exchangeId,
                       Long offerId,
                       Long wishId,
                       Boolean isBoth,
                       LocalDateTime createAt) {
        this.exchangeId = exchangeId;
        this.offerId = offerId;
        this.wishId = wishId;
        this.isBoth = isBoth;
        this.createAt = createAt;
    }

    public Long getExchangeId() {
        return exchangeId;
    }

    public void setExchangeId(Long exchangeId) {
        this.exchangeId = exchangeId;
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

    public Boolean getIsBoth() {
        return isBoth;
    }

    public void setIsBoth(Boolean isBoth) {
        this.isBoth = isBoth;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }
}

