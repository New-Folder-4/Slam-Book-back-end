package com.system.slam.controller.list;

import com.system.slam.entity.list.ExchangeList;
import com.system.slam.entity.list.UserExchangeList;
import com.system.slam.service.list.ExchangeListService;
import com.system.slam.web.dto.*;
import com.system.slam.service.MatchingService;
import com.system.slam.web.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/exchange")
public class ExchangeListController {

    private final MatchingService matchingService;
    private final ExchangeListService exchangeListService;

    @Autowired
    public ExchangeListController(MatchingService matchingService,
                                  ExchangeListService exchangeListService) {
        this.matchingService = matchingService;
        this.exchangeListService = exchangeListService;
    }

    @GetMapping("/matches")
    public List<ExchangeMatchDto> getMatches() {
        Long userId = getCurrentUserId();
        return matchingService.findMatchesForUser(userId);
    }

    @PostMapping("/propose")
    public ExchangeDto proposeExchange(@RequestBody ProposeRequest request) {
        ExchangeList saved = exchangeListService.proposeExchange(
                request.getOfferId(),
                request.getWishId()
        );
        return convertToDto(saved);
    }

    @PostMapping("/confirm/{exchangeId}")
    public ExchangeDto confirmExchange(@PathVariable Long exchangeId,
                                       @RequestBody ConfirmRequest request) {
        ExchangeList confirmed = exchangeListService.confirmExchange(
                exchangeId,
                request.getSecondOfferId(),
                request.getSecondWishId()
        );
        return convertToDto(confirmed);
    }

    @PostMapping("/track")
    public ExchangeDto setTrackNumber(@RequestBody TrackNumberRequest dto) {
        UserExchangeList updated = exchangeListService.setTrackNumber(
                dto.getExchangeId(),
                dto.getUserId(),
                dto.getTrackNumber()
        );
        return convertToDto(updated.getExchangeList());
    }

    @PostMapping("/receive")
    public ExchangeDto receiveBook(@RequestBody ReceiveRequest req) {
        ExchangeList exch = exchangeListService.receiveBook(req.getExchangeId(), req.getUserId());
        return convertToDto(exch);
    }

    private ExchangeDto convertToDto(ExchangeList exch) {
        ExchangeDto dto = new ExchangeDto();
        dto.setExchangeId(exch.getIdExchangeList());
        dto.setOfferId((exch.getOfferList1() != null) ? exch.getOfferList1().getIdOfferList() : null);
        dto.setWishId((exch.getWishList1() != null) ? exch.getWishList1().getIdWishList() : null);
        dto.setIsBoth(exch.isBoth());
        dto.setCreateAt(exch.getCreateAt());
        return dto;
    }

    private Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long currentUserId = userDetails.getId();
        return currentUserId;
    }
}

