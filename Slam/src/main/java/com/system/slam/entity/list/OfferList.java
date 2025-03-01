package com.system.slam.entity.list;

import com.system.slam.entity.BookLiterary;
import com.system.slam.entity.Status;
import com.system.slam.entity.User;
import lombok.*;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "OfferList")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OfferList {

    @Id
    @Column(name = "IdOfferList", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOfferList;

    @ManyToOne
    @JoinColumn(name = "IdBookLiterary", nullable = false)
    private BookLiterary bookLiterary;

    @ManyToOne
    @JoinColumn(name = "IdUser", nullable = false)
    private User user;

    @Column(name = "ISBN", length = 13)
    private String isbn;

    @Column(name = "YearPublishing", nullable = false)
    private LocalDateTime yearPublishing;

    @Column(name = "CreateAt", nullable = false)
    private LocalDateTime createAt;

    @Column(name = "UpdateAt", nullable = false)
    private LocalDateTime updateAt;

    @ManyToOne
    @JoinColumn(name = "IdStatus", nullable = false)
    private Status status;

}