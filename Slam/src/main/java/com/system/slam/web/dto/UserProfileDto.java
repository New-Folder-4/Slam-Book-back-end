package com.system.slam.web.dto;

public class UserProfileDto {
    private Long idUser;
    private String firstName;
    private String lastName;

    private String secondName;
    private String email;
    private String userName;
    private Integer rating;

    private String avatarUrl;

    public UserProfileDto() {
    }

    public UserProfileDto(Long idUser, String firstName, String lastName, String secondName,
                          String email, String userName, Integer rating, String avatarUrl) {
        this.idUser = idUser;
        this.firstName = firstName;
        this.lastName = lastName;
        this.secondName = secondName;
        this.email = email;
        this.userName = userName;
        this.rating = rating;
        this.avatarUrl = avatarUrl;
    }


    public String getSecondName() {
        return secondName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
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

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

}
