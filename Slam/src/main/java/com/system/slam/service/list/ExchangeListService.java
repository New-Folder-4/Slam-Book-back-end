package com.system.slam.service.list;

import com.system.slam.entity.Status;
import com.system.slam.entity.list.ExchangeList;
import com.system.slam.entity.list.OfferList;
import com.system.slam.entity.list.UserExchangeList;
import com.system.slam.entity.list.WishList;
import com.system.slam.repository.StatusRepository;
import com.system.slam.repository.list.ExchangeListRepository;
import com.system.slam.repository.list.OfferListRepository;
import com.system.slam.repository.list.UserExchangeListRepository;
import com.system.slam.repository.list.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class ExchangeListService {

    private final ExchangeListRepository exchangeListRepository;
    private final OfferListRepository offerListRepository;
    private final WishListRepository wishListRepository;
    private final UserExchangeListRepository userExchangeListRepository;
    private final StatusRepository statusRepository;

    @Autowired
    public ExchangeListService(ExchangeListRepository exchangeListRepository,
                               OfferListRepository offerListRepository,
                               WishListRepository wishListRepository,
                               UserExchangeListRepository userExchangeListRepository,
                               StatusRepository statusRepository) {
        this.exchangeListRepository = exchangeListRepository;
        this.offerListRepository = offerListRepository;
        this.wishListRepository = wishListRepository;
        this.userExchangeListRepository = userExchangeListRepository;
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

        createUserExchangeListRecord(exchange, exchange.getOfferList1());
        createUserExchangeListRecord(exchange, exchange.getOfferList2());
        return exchange;
    }

    private void createUserExchangeListRecord(ExchangeList exchange, OfferList offer) {
        UserExchangeList uex = new UserExchangeList();
        uex.setExchangeList(exchange);
        uex.setOfferList(offer);
        uex.setTrackNumber(null);
        uex.setReceiving(false);
        userExchangeListRepository.save(uex);
    }

    public UserExchangeList setTrackNumber(Long exchangeId, Long userId, String trackNumber) {
        ExchangeList exchange = exchangeListRepository.findById(exchangeId)
                .orElseThrow(() -> new RuntimeException("Exchange not found: " + exchangeId));

        OfferList userOffer;
        if (exchange.getOfferList1() != null &&
                exchange.getOfferList1().getUser().getIdUser().equals(userId)) {
            userOffer = exchange.getOfferList1();
        }
        else if (exchange.getOfferList2() != null &&
                exchange.getOfferList2().getUser().getIdUser().equals(userId)) {
            userOffer = exchange.getOfferList2();
        } else {
            userOffer = null;
        }
        if (userOffer == null) {
            throw new RuntimeException("User is not a participant in this exchange.");
        }

        UserExchangeList uex = userExchangeListRepository
                .findAll().stream()
                .filter(ue -> ue.getExchangeList().getIdExchangeList().equals(exchangeId))
                .filter(ue -> ue.getOfferList().getIdOfferList().equals(userOffer.getIdOfferList()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("UserExchangeList record not found"));

        uex.setTrackNumber(trackNumber);
        userExchangeListRepository.save(uex);

        Status shippedStatus = statusRepository.findByName("shipped")
                .orElseThrow(() -> new RuntimeException("Status 'shipped' not found"));
        userOffer.setStatus(shippedStatus);
        userOffer.setUpdateAt(LocalDateTime.now());
        offerListRepository.save(userOffer);

        return uex;
    }

    public UserExchangeList getUserExchangeList(Long exchangeId, Long userId) {
        ExchangeList exchange = exchangeListRepository.findById(exchangeId)
                .orElseThrow(() -> new RuntimeException("Exchange not found: " + exchangeId));
        OfferList userOffer;
        if (exchange.getOfferList1().getUser().getIdUser().equals(userId)) {
            userOffer = exchange.getOfferList1();
        } else if (exchange.getOfferList2() != null && exchange.getOfferList2().getUser().getIdUser().equals(userId)) {
            userOffer = exchange.getOfferList2();
        } else {
            userOffer = null;
        }
        if (userOffer == null) {
            throw new RuntimeException("User is not a participant in this exchange.");
        }
        return userExchangeListRepository.findAll().stream()
                .filter(ue -> ue.getExchangeList().getIdExchangeList().equals(exchangeId))
                .filter(ue -> ue.getOfferList().getIdOfferList().equals(userOffer.getIdOfferList()))
                .findFirst()
                .orElse(null);
    }
    public String getTrackNumber(Long exchangeId, Long userId) {
        UserExchangeList uex = getUserExchangeList(exchangeId, userId);
        return (uex != null) ? uex.getTrackNumber() : null;
    }

}

