package com.system.slam.entity.list;

import lombok.*;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "ExchangeList")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExchangeList {

    @Id
    @Column(name = "IdExchangeList", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idExchangeList;

    @ManyToOne
    @JoinColumn(name = "IdOfferList1", nullable = false)
    private OfferList offerList1;

    @ManyToOne
    @JoinColumn(name = "IdWishList1", nullable = false)
    private WishList wishList1;

    @ManyToOne
    @JoinColumn(name = "IdOfferList2", nullable = false)
    private OfferList offerList2;

    @ManyToOne
    @JoinColumn(name = "IdWishList2", nullable = false)
    private WishList wishList2;

    @Column(name = "CreateAt", nullable = false)
    private LocalDateTime createAt;

    @Column(name = "IsBoth", nullable = false)
    private boolean isBoth;

}

