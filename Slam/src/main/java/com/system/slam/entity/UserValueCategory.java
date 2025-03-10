package com.system.slam.entity;

import com.system.slam.entity.list.UserList;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "UserValueCategory")
public class UserValueCategory {

    @Id
    @Column(name = "IdUserValueCategory", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUserValueCategory;

    @ManyToOne
    @JoinColumn(name = "IdUserList", nullable = false)
    private UserList userList;

    @ManyToOne
    @JoinColumn(name = "IdCategory", nullable = false)
    private Category category;

    public UserValueCategory() {}

    public UserValueCategory(Long idUserValueCategory,
                             UserList userList,
                             Category category) {
        this.idUserValueCategory = idUserValueCategory;
        this.userList = userList;
        this.category = category;
    }

    public Long getIdUserValueCategory() {
        return idUserValueCategory;
    }

    public void setIdUserValueCategory(Long idUserValueCategory) {
        this.idUserValueCategory = idUserValueCategory;
    }

    public UserList getUserList() {
        return userList;
    }

    public void setUserList(UserList userList) {
        this.userList = userList;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        UserValueCategory that = (UserValueCategory) object;
        return Objects.equals(idUserValueCategory, that.idUserValueCategory) &&
                Objects.equals(userList, that.userList) &&
                Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUserValueCategory, userList, category);
    }

    @Override
    public String toString() {
        return "UserValueCategory{" +
                "idUserValueCategory=" + idUserValueCategory +
                ", userList=" + userList +
                ", category=" + category +
                '}';
    }
}

