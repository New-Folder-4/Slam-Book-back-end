package com.system.slam.entity.list;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "UserExchangeList")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserExchangeList {

    @Id
    @Column(name = "IdUserExchangeList", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUserExchangeList;

    @ManyToOne
    @JoinColumn(name = "IdExchangeList", nullable = false)
    private ExchangeList exchangeList;

    @ManyToOne
    @JoinColumn(name = "IdOfferList", nullable = false)
    private OfferList offerList;

    @Column(name = "TrackNumber", length = 20)
    private String trackNumber;

    @Column(name = "Receiving", nullable = false)
    private boolean receiving;

}

