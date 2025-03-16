package com.system.slam.service.list;

import com.system.slam.entity.Status;
import com.system.slam.entity.list.ExchangeList;
import com.system.slam.entity.list.OfferList;
import com.system.slam.entity.list.WishList;
import com.system.slam.repository.StatusRepository;
import com.system.slam.repository.list.ExchangeListRepository;
import com.system.slam.repository.list.OfferListRepository;
import com.system.slam.repository.list.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class ExchangeListService {

    private final ExchangeListRepository exchangeListRepository;
    private final OfferListRepository offerListRepository;
    private final WishListRepository wishListRepository;
    private final StatusRepository statusRepository;

    @Autowired
    public ExchangeListService(ExchangeListRepository exchangeListRepository,
                               OfferListRepository offerListRepository,
                               WishListRepository wishListRepository,
                               StatusRepository statusRepository) {
        this.exchangeListRepository = exchangeListRepository;
        this.offerListRepository = offerListRepository;
        this.wishListRepository = wishListRepository;
        this.statusRepository = statusRepository;
    }

    public ExchangeList proposeExchange(Long offerId, Long wishId) {
        OfferList offer = offerListRepository.findById(offerId)
                .orElseThrow(() -> new RuntimeException("Offer not found: " + offerId));

        WishList wish = wishListRepository.findById(wishId)
                .orElseThrow(() -> new RuntimeException("Wish not found: " + wishId));

        ExchangeList exchange = new ExchangeList();
        exchange.setOfferList1(offer);
        exchange.setWishList1(wish);

        exchange.setOfferList2(null);
        exchange.setWishList2(null);
        exchange.setCreateAt(LocalDateTime.now());
        exchange.setBoth(false);

        exchangeListRepository.save(exchange);

        Status reservedStatus = statusRepository.findByName("reserved")
                .orElseThrow(() -> new RuntimeException("Status 'reserved' not found"));

        offer.setStatus(reservedStatus);
        offer.setUpdateAt(LocalDateTime.now());
        offerListRepository.save(offer);

        wish.setStatus(reservedStatus);
        wish.setUpdateAt(LocalDateTime.now());
        wishListRepository.save(wish);

        return exchange;
    }

    public ExchangeList confirmExchange(Long exchangeId, Long secondOfferId, Long secondWishId) {
        ExchangeList exchange = exchangeListRepository.findById(exchangeId)
                .orElseThrow(() -> new RuntimeException("Exchange not found: " + exchangeId));

        OfferList offer2 = offerListRepository.findById(secondOfferId)
                .orElseThrow(() -> new RuntimeException("Offer2 not found: " + secondOfferId));
        WishList wish2 = wishListRepository.findById(secondWishId)
                .orElseThrow(() -> new RuntimeException("Wish2 not found: " + secondWishId));

        exchange.setOfferList2(offer2);
        exchange.setWishList2(wish2);
        exchange.setBoth(true);
        exchange.setCreateAt(LocalDateTime.now());
        exchangeListRepository.save(exchange);

        Status confirmedStatus = statusRepository.findByName("confirmed")
                .orElseThrow(() -> new RuntimeException("Status 'confirmed' not found"));

        offer2.setStatus(confirmedStatus);
        offer2.setUpdateAt(LocalDateTime.now());
        offerListRepository.save(offer2);

        wish2.setStatus(confirmedStatus);
        wish2.setUpdateAt(LocalDateTime.now());
        wishListRepository.save(wish2);

        return exchange;
    }

}
