package com.system.slam.entity;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "Autor")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Autor {

    @Id
    @Column(name = "IdAutor", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAutor;

    @Column(name = "LastName", length = 20, nullable = false)
    private String lastName;

    @Column(name = "FirstName", length = 20)
    private String firstName;

}