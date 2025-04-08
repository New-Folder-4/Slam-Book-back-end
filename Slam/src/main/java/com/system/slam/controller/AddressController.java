package com.system.slam.controller;

import com.system.slam.service.SecurityContextService;
import com.system.slam.web.dto.UserAddressDto;
import com.system.slam.service.UserAddressService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/addresses")
public class AddressController {

    private final UserAddressService userAddressService;
    private final SecurityContextService securityContextService;

    public AddressController(UserAddressService userAddressService, SecurityContextService securityContextService) {
        this.userAddressService = userAddressService;
        this.securityContextService = securityContextService;
    }

    @GetMapping
    public List<UserAddressDto> getAddresses() {
        Long userId = securityContextService.getCurrentUserId();
        return userAddressService.getAllAddressesOfUser(userId);
    }

    @PostMapping
    public UserAddressDto createAddress(@Valid @RequestBody UserAddressDto dto) {
        Long userId = securityContextService.getCurrentUserId();
        return userAddressService.createAddress(userId, dto);
    }

    @PutMapping
    public UserAddressDto updateAddress(@Valid @RequestBody UserAddressDto dto) {
        Long userId = securityContextService.getCurrentUserId();
        return userAddressService.updateAddress(userId, dto);
    }

    @DeleteMapping
    public void deleteAddress(@RequestBody UserAddressDto dto) {
        Long userId = securityContextService.getCurrentUserId();
        userAddressService.deleteAddress(userId);
    }

}
