package com.system.slam.entity.list;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "ExchangeList")
public class ExchangeList {

    @Id
    @Column(name = "IdExchangeList", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idExchangeList;

    @ManyToOne
    @JoinColumn(name = "IdOfferList1", nullable = false)
    private OfferList offerList1;

    @ManyToOne
    @JoinColumn(name = "IdWishList1", nullable = false)
    private WishList wishList1;

    @ManyToOne
    @JoinColumn(name = "IdOfferList2", nullable = false)
    private OfferList offerList2;

    @ManyToOne
    @JoinColumn(name = "IdWishList2", nullable = false)
    private WishList wishList2;

    @Column(name = "CreateAt", nullable = false)
    private LocalDateTime createAt;

    @Column(name = "IsBoth", nullable = false)
    private boolean isBoth;

    public ExchangeList() {}

    public ExchangeList(Long idExchangeList,
                        OfferList offerList1,
                        WishList wishList1,
                        OfferList offerList2,
                        WishList wishList2,
                        LocalDateTime createAt,
                        boolean isBoth) {
        this.idExchangeList = idExchangeList;
        this.offerList1 = offerList1;
        this.wishList1 = wishList1;
        this.offerList2 = offerList2;
        this.wishList2 = wishList2;
        this.createAt = createAt;
        this.isBoth = isBoth;
    }

    public Long getIdExchangeList() {
        return idExchangeList;
    }

    public void setIdExchangeList(Long idExchangeList) {
        this.idExchangeList = idExchangeList;
    }

    public OfferList getOfferList1() {
        return offerList1;
    }

    public void setOfferList1(OfferList offerList1) {
        this.offerList1 = offerList1;
    }

    public WishList getWishList1() {
        return wishList1;
    }

    public void setWishList1(WishList wishList1) {
        this.wishList1 = wishList1;
    }

    public OfferList getOfferList2() {
        return offerList2;
    }

    public void setOfferList2(OfferList offerList2) {
        this.offerList2 = offerList2;
    }

    public WishList getWishList2() {
        return wishList2;
    }

    public void setWishList2(WishList wishList2) {
        this.wishList2 = wishList2;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public boolean isBoth() {
        return isBoth;
    }

    public void setBoth(boolean both) {
        isBoth = both;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ExchangeList that = (ExchangeList) object;
        return isBoth == that.isBoth &&
                Objects.equals(idExchangeList, that.idExchangeList) &&
                Objects.equals(offerList1, that.offerList1) &&
                Objects.equals(wishList1, that.wishList1) &&
                Objects.equals(offerList2, that.offerList2) &&
                Objects.equals(wishList2, that.wishList2) &&
                Objects.equals(createAt, that.createAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idExchangeList, offerList1, wishList1,
                offerList2, wishList2, createAt, isBoth);
    }

    @Override
    public String toString() {
        return "ExchangeList{" +
                "idExchangeList=" + idExchangeList +
                ", offerList1=" + offerList1 +
                ", wishList1=" + wishList1 +
                ", offerList2=" + offerList2 +
                ", wishList2=" + wishList2 +
                ", createAt=" + createAt +
                ", isBoth=" + isBoth +
                '}';
    }
}

