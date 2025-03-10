package com.system.slam.web.dto;

public class UserAddressDto {
    private Long idUserAddress;
    private String addrIndex;
    private String addrCity;
    private String addrStreet;
    private String addrHouse;
    private String addrStructure;
    private String addrApart;
    private boolean isDefault;

    public UserAddressDto() {}

    public UserAddressDto(Long idUserAddress,
                          String addrIndex,
                          String addrCity,
                          String addrStreet,
                          String addrHouse,
                          String addrStructure,
                          String addrApart,
                          boolean isDefault) {
        this.idUserAddress = idUserAddress;
        this.addrIndex = addrIndex;
        this.addrCity = addrCity;
        this.addrStreet = addrStreet;
        this.addrHouse = addrHouse;
        this.addrStructure = addrStructure;
        this.addrApart = addrApart;
        this.isDefault = isDefault;
    }

    public Long getIdUserAddress() {
        return idUserAddress;
    }

    public void setIdUserAddress(Long idUserAddress) {
        this.idUserAddress = idUserAddress;
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
}
