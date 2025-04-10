package com.system.slam.entity.list;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "UserList")
public class UserList {

    @Id
    @Column(name = "IdUserList", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idUserList;

    @Column(name = "TypeList", nullable = false)
    private Integer typeList;

    @Column(name = "IdList", nullable = false)
    private Long idList;

    public UserList() {}

    public UserList(Long idUserList,
                    Integer typeList,
                    Long idList) {
        this.idUserList = idUserList;
        this.typeList = typeList;
        this.idList = idList;
    }

    public Long getIdUserList() {
        return idUserList;
    }

    public void setIdUserList(Long idUserList) {
        this.idUserList = idUserList;
    }

    public Integer getTypeList() {
        return typeList;
    }

    public void setTypeList(Integer typeList) {
        this.typeList = typeList;
    }

    public Long getIdList() {
        return idList;
    }

    public void setIdList(Long idList) {
        this.idList = idList;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        UserList userList = (UserList) object;
        return Objects.equals(idUserList, userList.idUserList) &&
                Objects.equals(typeList, userList.typeList) &&
                Objects.equals(idList, userList.idList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUserList, typeList, idList);
    }

    @Override
    public String toString() {
        return "UserList{" +
                "idUserList=" + idUserList +
                ", typeList=" + typeList +
                ", idList=" + idList +
                '}';
    }
}

