package com.system.slam.entity.list;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "UserList")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserList {

    @Id
    @Column(name = "IdUserList", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUserList;

    @Column(name = "TypeList", nullable = false)
    private Integer typeList;

    @Column(name = "IdList", nullable = false)
    private Integer idList;
}

