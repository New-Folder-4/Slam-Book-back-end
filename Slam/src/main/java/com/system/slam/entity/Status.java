package com.system.slam.entity;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Status")
public class Status {

    @Id
    @Column(name = "IdStatus", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStatus;

    @Column(name = "Name", length = 10)
    private String name;

    public Status() {}

    public Status(Long idStatus,
                  String name) {
        this.idStatus = idStatus;
        this.name = name;
    }

    public Long getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Long idStatus) {
        this.idStatus = idStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Status status = (Status) object;
        return Objects.equals(idStatus, status.idStatus) &&
                Objects.equals(name, status.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idStatus, name);
    }

    @Override
    public String toString() {
        return "Status{" +
                "idStatus=" + idStatus +
                ", name='" + name + '\'' +
                '}';
    }
}
