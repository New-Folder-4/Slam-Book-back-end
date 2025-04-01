package com.system.slam.entity;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "UserAddress")
public class UserAddress {

    @Id
    @Column(name = "IdUserAddress", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idUserAddress;

    @ManyToOne
    @JoinColumn(name = "IdUser", nullable = false)
    private User user;

    @Column(name = "AddrIndex", length = 6, nullable = false)
    private String addrIndex;

    @Column(name = "AddrCity", length = 15, nullable = false)
    private String addrCity;

    @Column(name = "AddrStreet", length = 25, nullable = false)
    private String addrStreet;

    @Column(name = "AddrHouse", length = 5, nullable = false)
    private String addrHouse;

    @Column(name = "AddrStructure", length = 10)
    private String addrStructure;

    @Column(name = "AddrApart", length = 3)
    private String addrApart;

    @Column(name = "IsDefault", nullable = false)
    private boolean isDefault;

    public UserAddress() {}

    public UserAddress(Long idUserAddress,
                       User user,
                       String addrIndex,
                       String addrCity,
                       String addrStreet,
                       String addrHouse,
                       String addrStructure,
                       String addrApart,
                       boolean isDefault) {
        this.idUserAddress = idUserAddress;
        this.user = user;
        this.addrIndex = addrIndex;
        this.addrCity = addrCity;
        this.addrStreet = addrStreet;
        this.addrHouse = addrHouse;
        this.addrStructure = addrStructure;
        this.addrApart = addrApart;
        this.isDefault = isDefault;
    }

    public UserAddress(Long idUserAddress) {
        this.idUserAddress = idUserAddress;
    }

    public Long getIdUserAddress() {
        return idUserAddress;
    }

    public void setIdUserAddress(Long idUserAddress) {
        this.idUserAddress = idUserAddress;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAddrIndex() {
        return addrIndex;
    }

    public void setAddrIndex(String addrIndex) {
        this.addrIndex = addrIndex;
    }

    public String getAddrCity() {
        return addrCity;
    }

    public void setAddrCity(String addrCity) {
        this.addrCity = addrCity;
    }

    public String getAddrStreet() {
        return addrStreet;
    }

    public void setAddrStreet(String addrStreet) {
        this.addrStreet = addrStreet;
    }

    public String getAddrHouse() {
        return addrHouse;
    }

    public void setAddrHouse(String addrHouse) {
        this.addrHouse = addrHouse;
    }

    public String getAddrStructure() {
        return addrStructure;
    }

    public void setAddrStructure(String addrStructure) {
        this.addrStructure = addrStructure;
    }

    public String getAddrApart() {
        return addrApart;
    }

    public void setAddrApart(String addrApart) {
        this.addrApart = addrApart;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        UserAddress that = (UserAddress) object;
        return isDefault == that.isDefault &&
                Objects.equals(idUserAddress, that.idUserAddress) &&
                Objects.equals(user, that.user) &&
                Objects.equals(addrIndex, that.addrIndex) &&
                Objects.equals(addrCity, that.addrCity) &&
                Objects.equals(addrStreet, that.addrStreet) &&
                Objects.equals(addrHouse, that.addrHouse) &&
                Objects.equals(addrStructure, that.addrStructure) &&
                Objects.equals(addrApart, that.addrApart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUserAddress, user, addrIndex, addrCity,
                addrStreet, addrHouse, addrStructure, addrApart, isDefault);
    }

    @Override
    public String toString() {
        return "UserAddress{" +
                "idUserAddress=" + idUserAddress +
                ", user=" + user +
                ", addrIndex='" + addrIndex + '\'' +
                ", addrCity='" + addrCity + '\'' +
                ", addrStreet='" + addrStreet + '\'' +
                ", addrHouse='" + addrHouse + '\'' +
                ", addrStructure='" + addrStructure + '\'' +
                ", addrApart='" + addrApart + '\'' +
                ", isDefault=" + isDefault +
                '}';
    }
}
