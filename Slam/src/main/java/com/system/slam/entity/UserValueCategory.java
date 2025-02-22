package com.system.slam.entity;

import com.system.slam.entity.list.UserList;
import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "UserValueCategory")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserValueCategory {

    @Id
    @Column(name = "IdUserValueCategory", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUserValueCategory;

    @ManyToOne
    @JoinColumn(name = "IdUserList", nullable = false)
    private UserList userList;

    @ManyToOne
    @JoinColumn(name = "IdCategory", nullable = false)
    private Category category;

}

