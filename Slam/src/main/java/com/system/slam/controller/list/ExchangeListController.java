package com.system.slam.controller.list;

import com.system.slam.entity.list.ExchangeList;
import com.system.slam.service.list.ExchangeListService;
import com.system.slam.web.dto.ConfirmRequest;
import com.system.slam.web.dto.ExchangeDto;
import com.system.slam.web.dto.ExchangeMatchDto;
import com.system.slam.service.MatchingService;
import com.system.slam.web.dto.ProposeRequest;
import org.springframework.beans.factory.annotation.Autowired;
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

    private ExchangeDto convertToDto(ExchangeList exchange) {
        ExchangeDto dto = new ExchangeDto();
        dto.setExchangeId(exchange.getIdExchangeList());
        dto.setOfferId((exchange.getOfferList1() != null) ? exchange.getOfferList1().getIdOfferList() : null);
        dto.setWishId((exchange.getWishList1() != null) ? exchange.getWishList1().getIdWishList() : null);
        dto.setIsBoth(exchange.isBoth());
        dto.setCreateAt(exchange.getCreateAt());
        return dto;
    }

    private Long getCurrentUserId() {
        return 1L; // тест
    }
}

