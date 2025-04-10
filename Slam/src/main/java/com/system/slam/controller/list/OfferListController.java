package com.system.slam.controller.list;

import com.system.slam.service.SecurityContextService;
import com.system.slam.web.dto.OfferDto;
import com.system.slam.entity.list.OfferList;
import com.system.slam.service.list.OfferListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/offers")
public class OfferListController {

    private final OfferListService offerListService;
    private final SecurityContextService securityContextService;

    @Autowired
    public OfferListController(OfferListService offerListService,
                               SecurityContextService securityContextService) {
        this.offerListService = offerListService;
        this.securityContextService = securityContextService;
    }

    @PostMapping
    public OfferDto createOffer(@RequestBody OfferDto dto) {
        Long userId = securityContextService.getCurrentUserId();
        OfferList saved = offerListService.createOffer(
                userId,
                dto.getBookLiteraryId(),
                dto.getIsbn(),
                dto.getYearPublishing(),
                dto.getStatus(),
                dto.getCategoryIds()
        );
        return convertToDto(saved, dto.getCategoryIds());
    }

    @PutMapping
    public OfferDto updateOffer(@RequestBody OfferDto dto) {
        Long userId = securityContextService.getCurrentUserId();
        OfferList updated = offerListService.updateOffer(
                userId,
                dto.getIsbn(),
                dto.getYearPublishing(),
                dto.getStatus(),
                dto.getCategoryIds()
        );
        return convertToDto(updated, dto.getCategoryIds());
    }

    @DeleteMapping
    public void deleteOffer(@RequestParam(defaultValue = "false") boolean physicalDelete) {
        Long userId = securityContextService.getCurrentUserId();
        offerListService.deleteOffer(userId, physicalDelete);
    }

    @GetMapping("/my")
    public List<OfferDto> getMyOffers() {
        Long userId = securityContextService.getCurrentUserId();
        List<OfferList> allOffers = offerListService.getAllOffersByUser(userId);

        List<OfferDto> result = new ArrayList<>();
        for (OfferList offer : allOffers) {
            List<Long> catIds = getCategoryIdsForOffer(offer.getIdOfferList());
            result.add(convertToDto(offer, catIds));
        }
        return result;
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

