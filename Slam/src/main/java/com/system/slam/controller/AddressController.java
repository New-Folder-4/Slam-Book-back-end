package com.system.slam.controller;

import com.system.slam.web.dto.UserAddressDto;
import com.system.slam.service.UserAddressService;
import com.system.slam.web.security.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/addresses")
public class AddressController {

    private final UserAddressService userAddressService;

    public AddressController(UserAddressService userAddressService) {
        this.userAddressService = userAddressService;
    }

    @GetMapping
    public List<UserAddressDto> getAddresses() {
        Long userId = getCurrentUserId();
        return userAddressService.getAllAddressesOfUser(userId);
    }

    @PostMapping
    public UserAddressDto createAddress(@RequestBody UserAddressDto dto) {
        Long userId = getCurrentUserId();
        return userAddressService.createAddress(userId, dto);
    }

    @PutMapping
    public UserAddressDto updateAddress(@RequestBody UserAddressDto dto) {
        Long userId = getCurrentUserId();
        return userAddressService.updateAddress(userId, dto);
    }

    @DeleteMapping
    public void deleteAddress(@RequestBody UserAddressDto dto) {
        Long userId = getCurrentUserId();
        userAddressService.deleteAddress(userId);
    }

    private Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long currentUserId = userDetails.getId();
        return currentUserId;
    }
}
