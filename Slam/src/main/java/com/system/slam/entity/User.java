package com.system.slam.entity;

import lombok.*;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "User")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @Column(name = "IdUser", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    @Column(name = "FirstName", length = 25, nullable = false)
    private String firstName;

    @Column(name = "LastName", length = 50, nullable = false)
    private String lastName;

    @Column(name = "SecondName", length = 25)
    private String secondName;

    @Column(name = "Email", length = 15, nullable = false)
    private String email;

    @Column(name = "UserName", length = 20, nullable = false)
    private String userName;

    @Column(name = "Password", length = 15, nullable = false)
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

    public User(Long id) {
        this.idUser = id;
    }
}
