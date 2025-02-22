package com.system.slam.entity;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "BookLiterary")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookLiterary {

    @Id
    @Column(name = "IdBookLiterary", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idBookLiterary;

    @ManyToOne
    @JoinColumn(name = "IdAutor", nullable = false)
    private Autor autor;

    @Column(name = "BookName", length = 50, nullable = false)
    private String bookName;

    @Column(name = "Note", length = 50)
    private String note;

}
