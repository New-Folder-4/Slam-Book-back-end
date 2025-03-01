package com.system.slam.entity.list;

import com.system.slam.entity.Status;
import com.system.slam.entity.User;
import com.system.slam.entity.UserAddress;
import lombok.*;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "WishList")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WishList {

    @Id
    @Column(name = "IdWishList", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idWishList;

    @ManyToOne
    @JoinColumn(name = "IdUser", nullable = false)
    private User user;

    @Column(name = "CreateAt", nullable = false)
    private LocalDateTime createAt;

    @Column(name = "UpdateAt", nullable = false)
    private LocalDateTime updateAt;

    @ManyToOne
    @JoinColumn(name = "IdStatus", nullable = false)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "IdUserAddress", nullable = false)
    private UserAddress userAddress;

}
