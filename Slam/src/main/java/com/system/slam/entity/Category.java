package com.system.slam.entity;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "Category")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {

    @Id
    @Column(name = "IdCategory", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategory;

    @Column(name = "Name", length = 25, nullable = false)
    private String name;

    @Column(name = "IdParent", nullable = false)
    private Integer idParent;

    @Column(name = "MultiSelect", nullable = false)
    private boolean multiSelect;

}

