package com.system.slam.controller;

import com.system.slam.web.dto.UserAddressDto;
import com.system.slam.service.UserAddressService;
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

    @PutMapping("/{addressId}")
    public UserAddressDto updateAddress(@PathVariable Long addressId,
                                        @RequestBody UserAddressDto dto) {
        return userAddressService.updateAddress(addressId, dto);
    }

    @DeleteMapping("/{addressId}")
    public void deleteAddress(@PathVariable Long addressId) {
        userAddressService.deleteAddress(addressId);
    }

    private Long getCurrentUserId() {
        return 1L; // тест
    }
}
