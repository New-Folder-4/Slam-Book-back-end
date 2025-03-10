package com.system.slam.web.dto;

import java.time.LocalDateTime;
import java.util.List;

public class WishDto {
    private Long idWishList;
    private Long userId;
    private Long addressId;
    private String status;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private List<Long> categoryIds;

    public WishDto() {}

    public WishDto(Long idWishList,
                   Long userId,
                   Long addressId,
                   String status,
                   LocalDateTime createAt,
                   LocalDateTime updateAt,
                   List<Long> categoryIds) {
        this.idWishList = idWishList;
        this.userId = userId;
        this.addressId = addressId;
        this.status = status;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.categoryIds = categoryIds;
    }

    public Long getIdWishList() {
        return idWishList;
    }

    public void setIdWishList(Long idWishList) {
        this.idWishList = idWishList;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    public List<Long> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<Long> categoryIds) {
        this.categoryIds = categoryIds;
    }
}

