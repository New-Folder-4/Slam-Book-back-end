package com.system.slam.web.dto;

import java.util.List;

public class ExchangeMatchDto {
    private String matchType;
    private List<OfferDto> offers;

    public ExchangeMatchDto() {}

    public ExchangeMatchDto(String matchType,
                            List<OfferDto> offers) {
        this.matchType = matchType;
        this.offers = offers;
    }

    public String getMatchType() {
        return matchType;
    }

    public void setMatchType(String matchType) {
        this.matchType = matchType;
    }

    public List<OfferDto> getOffers() {
        return offers;
    }

    public void setOffers(List<OfferDto> offers) {
        this.offers = offers;
    }
}

