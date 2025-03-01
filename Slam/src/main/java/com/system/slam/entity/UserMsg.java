package com.system.slam.entity;

import lombok.*;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "UserMsg")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

}
