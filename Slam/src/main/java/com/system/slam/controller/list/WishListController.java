package com.system.slam.controller.list;

import com.system.slam.service.SecurityContextService;
import com.system.slam.web.dto.WishDto;
import com.system.slam.entity.list.WishList;
import com.system.slam.service.list.WishListService;
import com.system.slam.web.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/wishes")
public class WishListController {

    private final WishListService wishListService;
    private final SecurityContextService securityContextService;
    @Autowired
    public WishListController(WishListService wishListService,
                              SecurityContextService securityContextService) {
        this.wishListService = wishListService;
        this.securityContextService = securityContextService;
    }
    @PostMapping("/v2")
    public WishDto createWish2(@RequestBody WishDto dto) {

        Long userId = securityContextService.getCurrentUserId();
        if (userId == null) {
            throw new RuntimeException("User is not authentication!");
        }
        System.out.println("User ID: " + userId);

        try {
            WishList saved = wishListService.createWish(
                    userId,
                    userId,
                    dto.getStatus(),
                    dto.getCategoryIds() != null ? dto.getCategoryIds() : Collections.emptyList()
            );
            return convertToDto(saved, dto.getCategoryIds());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error while creating wish", e);
        }
    }

    @PostMapping
    public WishDto createWish(@RequestBody WishDto dto) {
        WishList saved = wishListService.createWish(
                dto.getUserId(),
                dto.getAddressId(),
                dto.getStatus(),
                dto.getCategoryIds()
        );
        return convertToDto(saved, dto.getCategoryIds());
    }

    @PutMapping("/{id}")
    public WishDto updateWish(@PathVariable Long id, @RequestBody WishDto dto) {
        WishList updated = wishListService.updateWish(
                id,
                dto.getAddressId(),
                dto.getStatus(),
                dto.getCategoryIds()
        );
        return convertToDto(updated, dto.getCategoryIds());
    }

    @DeleteMapping("/{id}")
    public void deleteWish(@PathVariable Long id,
                           @RequestParam(defaultValue = "false") boolean physicalDelete) {
        wishListService.deleteWish(id, physicalDelete);
    }

    @GetMapping("/my")
    public List<WishDto> getMyWishes() {
        Long userId = securityContextService.getCurrentUserId();
        List<WishList> allWishes = wishListService.getAllByUserId(userId);

        List<WishDto> result = new ArrayList<>();
        for (WishList wish : allWishes) {
            List<Long> catIds = wishListService.getCategoryIdsForWish(wish.getIdWishList());
            result.add(convertToDto(wish, catIds));
        }
        return result;
    }

    private WishDto convertToDto(WishList wish, List<Long> categoryIds) {
        WishDto dto = new WishDto();
        dto.setIdWishList(wish.getIdWishList());
        dto.setUserId((wish.getUser() != null) ? wish.getUser().getIdUser() : null);
        dto.setAddressId((wish.getUserAddress() != null) ? wish.getUserAddress().getIdUserAddress() : null);
        dto.setStatus((wish.getStatus() != null) ? wish.getStatus().getName() : null);
        dto.setCreateAt(wish.getCreateAt());
        dto.setUpdateAt(wish.getUpdateAt());
        dto.setCategoryIds(categoryIds);

        return dto;
    }
}

