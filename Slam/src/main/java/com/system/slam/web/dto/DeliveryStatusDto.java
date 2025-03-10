package com.system.slam.web.dto;

public class DeliveryStatusDto {
    private String trackNumber;
    private String status;

    public DeliveryStatusDto() {
    }

    public DeliveryStatusDto(String trackNumber,
                             String status) {
        this.trackNumber = trackNumber;
        this.status = status;
    }

    public String getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(String trackNumber) {
        this.trackNumber = trackNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
