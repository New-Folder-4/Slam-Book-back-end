package com.system.slam.service;

import com.system.slam.entity.list.OfferList;
import com.system.slam.entity.list.WishList;
import com.system.slam.repository.list.OfferListRepository;
import com.system.slam.repository.list.WishListRepository;
import com.system.slam.service.list.UserListService;
import com.system.slam.web.dto.ExchangeMatchDto;
import com.system.slam.web.dto.OfferDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MatchingService {

    private final WishListRepository wishListRepository;
    private final OfferListRepository offerListRepository;
    private final UserListService userListService;

    @Autowired
    public MatchingService(WishListRepository wishListRepository,
                           OfferListRepository offerListRepository,
                           UserListService userListService) {
        this.wishListRepository = wishListRepository;
        this.offerListRepository = offerListRepository;
        this.userListService = userListService;
    }

    public List<ExchangeMatchDto> findMatchesForUser(Long userId) {
        List<WishList> userWishes = getUserFreeWishes(userId);
        List<ExchangeMatchDto> result = new ArrayList<>();

        for (WishList wish : userWishes) {
            Set<Long> wishCategoryIds = new HashSet<>(userListService.getCategoryIdsForList(
                    wish.getIdWishList(), 2));
            List<OfferList> allOffers = getFreeOffersExceptUser(userId);

            List<OfferDto> fullMatches = new ArrayList<>();
            List<OfferDto> partialMatches = new ArrayList<>();

            for (OfferList offer : allOffers) {
                Set<Long> offerCategoryIds = new HashSet<>(userListService.getCategoryIdsForList(
                        offer.getIdOfferList(), 1));

                if (offerCategoryIds.containsAll(wishCategoryIds)) {
                    fullMatches.add(convertOfferToDto(offer));
                }
                else if (!getIntersection(offerCategoryIds, wishCategoryIds).isEmpty()) {
                    partialMatches.add(convertOfferToDto(offer));
                }
            }
            if (!fullMatches.isEmpty()) {
                ExchangeMatchDto fullDto = new ExchangeMatchDto("full match", fullMatches);
                result.add(fullDto);
            }
            if (!partialMatches.isEmpty()) {
                ExchangeMatchDto partialDto = new ExchangeMatchDto("partial match", partialMatches);
                result.add(partialDto);
            }
        }

        return result;
    }

    private List<WishList> getUserFreeWishes(Long userId) {
        return wishListRepository.findAll().stream()
                .filter(wish -> wish.getUser().getIdUser().equals(userId))
                .toList();
    }

    private List<OfferList> getFreeOffersExceptUser(Long userId) {
        return offerListRepository.findAll().stream()
                .filter(offer -> !offer.getUser().getIdUser().equals(userId))
                .toList();
    }

    private Set<Long> getIntersection(Set<Long> s1, Set<Long> s2) {
        Set<Long> intersection = new HashSet<>(s1);
        intersection.retainAll(s2);
        return intersection;
    }

    private OfferDto convertOfferToDto(OfferList offer) {
        OfferDto dto = new OfferDto();
        dto.setIdOfferList(offer.getIdOfferList());
        dto.setUserId(offer.getUser().getIdUser());
        if (offer.getBookLiterary() != null) {
            dto.setBookLiteraryId(offer.getBookLiterary().getIdBookLiterary());
        }
        dto.setIsbn(offer.getIsbn());
        dto.setYearPublishing(offer.getYearPublishing());
        dto.setCreateAt(offer.getCreateAt());
        dto.setUpdateAt(offer.getUpdateAt());
        dto.setStatus((offer.getStatus() != null) ? offer.getStatus().getName() : null);

        List<Long> catIds = userListService.getCategoryIdsForList(offer.getIdOfferList(), 1);
        dto.setCategoryIds(catIds);

        return dto;
    }
}

