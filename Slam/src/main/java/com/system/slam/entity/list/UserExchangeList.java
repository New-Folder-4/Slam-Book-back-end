package com.system.slam.entity.list;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "UserExchangeList")
public class UserExchangeList {

    @Id
    @Column(name = "IdUserExchangeList", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idUserExchangeList;

    @ManyToOne
    @JoinColumn(name = "IdExchangeList", nullable = false)
    private ExchangeList exchangeList;

    @ManyToOne
    @JoinColumn(name = "IdOfferList", nullable = false)
    private OfferList offerList;

    @Column(name = "TrackNumber", length = 20)
    private String trackNumber;

    @Column(name = "Receiving", nullable = false)
    private boolean receiving;

    public UserExchangeList() {}

    public UserExchangeList(Long idUserExchangeList,
                            ExchangeList exchangeList,
                            OfferList offerList,
                            String trackNumber,
                            boolean receiving) {
        this.idUserExchangeList = idUserExchangeList;
        this.exchangeList = exchangeList;
        this.offerList = offerList;
        this.trackNumber = trackNumber;
        this.receiving = receiving;
    }

    public Long getIdUserExchangeList() {
        return idUserExchangeList;
    }

    public void setIdUserExchangeList(Long idUserExchangeList) {
        this.idUserExchangeList = idUserExchangeList;
    }

    public ExchangeList getExchangeList() {
        return exchangeList;
    }

    public void setExchangeList(ExchangeList exchangeList) {
        this.exchangeList = exchangeList;
    }

    public OfferList getOfferList() {
        return offerList;
    }

    public void setOfferList(OfferList offerList) {
        this.offerList = offerList;
    }

    public String getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(String trackNumber) {
        this.trackNumber = trackNumber;
    }

    public boolean isReceiving() {
        return receiving;
    }

    public void setReceiving(boolean receiving) {
        this.receiving = receiving;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        UserExchangeList that = (UserExchangeList) object;
        return receiving == that.receiving &&
                Objects.equals(idUserExchangeList, that.idUserExchangeList) &&
                Objects.equals(exchangeList, that.exchangeList) &&
                Objects.equals(offerList, that.offerList) &&
                Objects.equals(trackNumber, that.trackNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUserExchangeList, exchangeList,
                offerList, trackNumber, receiving);
    }

    @Override
    public String toString() {
        return "UserExchangeList{" +
                "idUserExchangeList=" + idUserExchangeList +
                ", exchangeList=" + exchangeList +
                ", offerList=" + offerList +
                ", trackNumber='" + trackNumber + '\'' +
                ", receiving=" + receiving +
                '}';
    }
}

