package com.system.slam.entity;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "Status")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Status {

    @Id
    @Column(name = "IdStatus", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStatus;

    @Column(name = "Name", length = 10)
    private String name;

}
