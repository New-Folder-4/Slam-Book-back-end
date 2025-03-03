package com.system.slam.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "UserMsg")
public class UserMsg {

    @Id
    @Column(name = "IdUserMsg", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUserMsg;

    @ManyToOne
    @JoinColumn(name = "IdUser", nullable = false)
    private User user;

    @Column(name = "CreateAt", nullable = false)
    private LocalDateTime createAt;

    @Column(name = "Text", length = 250, nullable = false)
    private String text;

    @Column(name = "Notes", length = 150)
    private String notes;

    @ManyToOne
    @JoinColumn(name = "IdStatus", nullable = false)
    private Status status;

    @Column(name = "Type", nullable = false)
    private Integer type;

    public UserMsg() {}

    public UserMsg(Long idUserMsg,
                   User user,
                   LocalDateTime createAt,
                   String text,
                   String notes,
                   Status status,
                   Integer type) {
        this.idUserMsg = idUserMsg;
        this.user = user;
        this.createAt = createAt;
        this.text = text;
        this.notes = notes;
        this.status = status;
        this.type = type;
    }

    public Long getIdUserMsg() {
        return idUserMsg;
    }

    public void setIdUserMsg(Long idUserMsg) {
        this.idUserMsg = idUserMsg;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        UserMsg userMsg = (UserMsg) object;
        return Objects.equals(idUserMsg, userMsg.idUserMsg) &&
                Objects.equals(user, userMsg.user) &&
                Objects.equals(createAt, userMsg.createAt) &&
                Objects.equals(text, userMsg.text) &&
                Objects.equals(notes, userMsg.notes) &&
                Objects.equals(status, userMsg.status) &&
                Objects.equals(type, userMsg.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUserMsg, user, createAt,
                text, notes, status, type);
    }

    @Override
    public String toString() {
        return "UserMsg{" +
                "idUserMsg=" + idUserMsg +
                ", user=" + user +
                ", createAt=" + createAt +
                ", text='" + text + '\'' +
                ", notes='" + notes + '\'' +
                ", status=" + status +
                ", type=" + type +
                '}';
    }
}
