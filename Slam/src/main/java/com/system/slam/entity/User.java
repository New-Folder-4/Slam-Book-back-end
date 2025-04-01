package com.system.slam.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @Column(name = "IdUser", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idUser;

    @Column(name = "FirstName", length = 25, nullable = false)
    private String firstName;

    @Column(name = "LastName", length = 50, nullable = false)
    private String lastName;

    @Column(name = "SecondName", length = 25)
    private String secondName;

    @Column(name = "Email", length = 254, nullable = false)
    private String email;

    @Column(name = "UserName", length = 20, nullable = false)
    private String userName;

    @Column(name = "Password", length = 60, nullable = false)
    private String password;

    @Column(name = "Rating", nullable = false)
    private Integer rating;

    @Column(name = "CreateAt", nullable = false)
    private LocalDateTime createAt;

    @Column(name = "Enabled", nullable = false)
    private boolean enabled;

    @Lob
    @Column(name = "Avatar")
    private byte[] avatar;

    @Column(name = "IsStaff", nullable = false)
    private boolean isStaff;

    @Column(name = "IsSuperUser", nullable = false)
    private boolean isSuperUser;

    public User() {}

    public User(Long idUser,
                String firstName,
                String lastName,
                String secondName,
                String email,
                String userName,
                String password,
                Integer rating,
                LocalDateTime createAt,
                boolean enabled,
                byte[] avatar,
                boolean isStaff,
                boolean isSuperUser) {
        this.idUser = idUser;
        this.firstName = firstName;
        this.lastName = lastName;
        this.secondName = secondName;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.rating = rating;
        this.createAt = createAt;
        this.enabled = enabled;
        this.avatar = avatar;
        this.isStaff = isStaff;
        this.isSuperUser = isSuperUser;
    }

    public User(Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public boolean isStaff() {
        return isStaff;
    }

    public void setStaff(boolean staff) {
        isStaff = staff;
    }

    public boolean isSuperUser() {
        return isSuperUser;
    }

    public void setSuperUser(boolean superUser) {
        isSuperUser = superUser;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        User user = (User) object;
        return enabled == user.enabled &&
                isStaff == user.isStaff &&
                isSuperUser == user.isSuperUser &&
                Objects.equals(idUser, user.idUser) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(secondName, user.secondName) &&
                Objects.equals(email, user.email) &&
                Objects.equals(userName, user.userName) &&
                Objects.equals(password, user.password) &&
                Objects.equals(rating, user.rating) &&
                Objects.equals(createAt, user.createAt) &&
                Arrays.equals(avatar, user.avatar);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(idUser, firstName,
                lastName, secondName, email, userName,
                password, rating, createAt, enabled, isStaff, isSuperUser);
        result = 31 * result + Arrays.hashCode(avatar);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", rating=" + rating +
                ", createAt=" + createAt +
                ", enabled=" + enabled +
                ", avatar=" + Arrays.toString(avatar) +
                ", isStaff=" + isStaff +
                ", isSuperUser=" + isSuperUser +
                '}';
    }
}
