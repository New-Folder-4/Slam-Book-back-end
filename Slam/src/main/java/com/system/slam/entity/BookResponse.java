package com.system.slam.entity;

import lombok.*;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "BookResponse")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookResponse {

    @Id
    @Column(name = "IdBookResponse", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBookResponse;

    @ManyToOne
    @JoinColumn(name = "IdBookLiterary", nullable = false)
    private BookLiterary bookLiterary;

    @ManyToOne
    @JoinColumn(name = "IdUser", nullable = false)
    private User user;

    @Column(name = "CreateAt", nullable = false)
    private LocalDateTime createAt;

    @Column(name = "Response", length = 500, nullable = false)
    private String response;

    @Column(name = "Note", length = 50)
    private String note;

}
