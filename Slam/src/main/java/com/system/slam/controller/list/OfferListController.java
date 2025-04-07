package com.system.slam.controller.list;

import com.system.slam.web.dto.OfferDto;
import com.system.slam.entity.list.OfferList;
import com.system.slam.service.list.OfferListService;
import com.system.slam.web.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/offers")
public class OfferListController {

    private final OfferListService offerListService;

    @Autowired
    public OfferListController(OfferListService offerListService) {
        this.offerListService = offerListService;
    }

    @PostMapping
    public OfferDto createOffer(@RequestBody OfferDto dto) {
        OfferList saved = offerListService.createOffer(
                dto.getUserId(),
                dto.getBookLiteraryId(),
                dto.getIsbn(),
                dto.getYearPublishing(),
                dto.getStatus(),
                dto.getCategoryIds()
        );
        return convertToDto(saved, dto.getCategoryIds());
    }

    @PutMapping("/{id}")
    public OfferDto updateOffer(@PathVariable Long id, @RequestBody OfferDto dto) {
        OfferList updated = offerListService.updateOffer(
                id,
                dto.getIsbn(),
                dto.getYearPublishing(),
                dto.getStatus(),
                dto.getCategoryIds()
        );
        return convertToDto(updated, dto.getCategoryIds());
    }

    @DeleteMapping("/{id}")
    public void deleteOffer(@PathVariable Long id,
                            @RequestParam(defaultValue = "false") boolean physicalDelete) {
        offerListService.deleteOffer(id, physicalDelete);
    }

    @GetMapping("/my")
    public List<OfferDto> getMyOffers() {
        Long userId = getCurrentUserId();
        List<OfferList> allOffers = offerListService.getAllOffersByUser(userId);

        List<OfferDto> result = new ArrayList<>();
        for (OfferList offer : allOffers) {
            List<Long> catIds = getCategoryIdsForOffer(offer.getIdOfferList());
            result.add(convertToDto(offer, catIds));
        }
        return result;
    }

    private Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long currentUserId = userDetails.getId();
        return currentUserId;
    }
    private List<Long> getCategoryIdsForOffer(Long offerId) {
        return new ArrayList<>();
    }

    private OfferDto convertToDto(OfferList offer, List<Long> categoryIds) {
        OfferDto dto = new OfferDto();
        dto.setIdOfferList(offer.getIdOfferList());
        dto.setUserId((offer.getUser() != null) ? offer.getUser().getIdUser() : null);
        dto.setBookLiteraryId((offer.getBookLiterary() != null) ? offer.getBookLiterary().getIdBookLiterary() : null);
        dto.setIsbn(offer.getIsbn());
        dto.setYearPublishing(offer.getYearPublishing());
        dto.setCreateAt(offer.getCreateAt());
        dto.setUpdateAt(offer.getUpdateAt());
        dto.setStatus((offer.getStatus() != null) ? offer.getStatus().getName() : null);
        dto.setCategoryIds(categoryIds);
        return dto;
    }
}

