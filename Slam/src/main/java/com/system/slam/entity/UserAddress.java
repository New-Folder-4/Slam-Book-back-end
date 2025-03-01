package com.system.slam.entity;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "UserAddress")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAddress {

    @Id
    @Column(name = "IdUserAddress", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUserAddress;

    @ManyToOne
    @JoinColumn(name = "IdUser", nullable = false)
    private User user;

    @Column(name = "AddrIndex", length = 6, nullable = false)
    private String addrIndex;

    @Column(name = "AddrCity", length = 15, nullable = false)
    private String addrCity;

    @Column(name = "AddrStreet", length = 25, nullable = false)
    private String addrStreet;

    @Column(name = "AddrHouse", length = 5, nullable = false)
    private String addrHouse;

    @Column(name = "AddrStructure", length = 10)
    private String addrStructure;

    @Column(name = "AddrApart", length = 3)
    private String addrApart;

    @Column(name = "IsDefault", nullable = false)
    private boolean isDefault;

    public UserAddress(Long id) {
        this.idUserAddress = id;
    }

}
