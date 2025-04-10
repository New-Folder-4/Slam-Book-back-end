package com.system.slam.entity.list;

import com.system.slam.entity.Status;
import com.system.slam.entity.User;
import com.system.slam.entity.UserAddress;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "WishList")
public class WishList {

    @Id
    @Column(name = "IdWishList", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idWishList;

    @ManyToOne
    @JoinColumn(name = "IdUser", nullable = false)
    private User user;

    @Column(name = "CreateAt", nullable = false)
    private LocalDateTime createAt;

    @Column(name = "UpdateAt", nullable = false)
    private LocalDateTime updateAt;

    @ManyToOne
    @JoinColumn(name = "IdStatus", nullable = false)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "IdUserAddress", nullable = false)
    private UserAddress userAddress;

    @ElementCollection
    @CollectionTable(name = "wish_list_category_ids",
            joinColumns = @JoinColumn(name = "wish_list_id"))
    @Column(name = "categoryIds")
    private List<Long> categoryIds;

    public WishList() {}

    public WishList(Long idWishList,
                    User user,
                    LocalDateTime createAt,
                    LocalDateTime updateAt,
                    Status status,
                    UserAddress userAddress,
                    List<Long> categoryIds) {
        this.idWishList = idWishList;
        this.user = user;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.status = status;
        this.userAddress = userAddress;
        this.categoryIds = categoryIds;
    }

    public Long getIdWishList() {
        return idWishList;
    }

    public void setIdWishList(Long idWishList) {
        this.idWishList = idWishList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public UserAddress getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(UserAddress userAddress) {
        this.userAddress = userAddress;
    }

    public List<Long> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<Long> categoryIds) {
        this.categoryIds = categoryIds;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        WishList wishList = (WishList) object;
        return Objects.equals(idWishList, wishList.idWishList) &&
                Objects.equals(user, wishList.user) &&
                Objects.equals(createAt, wishList.createAt) &&
                Objects.equals(updateAt, wishList.updateAt) &&
                Objects.equals(status, wishList.status) &&
                Objects.equals(userAddress, wishList.userAddress) &&
                Objects.equals(categoryIds, wishList.categoryIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idWishList, user,
                createAt, updateAt, status,
                userAddress, categoryIds);
    }

    @Override
    public String toString() {
        return "WishList{" +
                "idWishList=" + idWishList +
                ", user=" + user +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                ", status=" + status +
                ", userAddress=" + userAddress +
                ", categoryIds=" + categoryIds +
                '}';
    }
}

