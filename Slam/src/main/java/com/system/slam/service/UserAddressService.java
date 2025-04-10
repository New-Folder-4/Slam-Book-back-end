package com.system.slam.service;

import com.system.slam.web.dto.UserAddressDto;
import com.system.slam.entity.User;
import com.system.slam.entity.UserAddress;
import com.system.slam.repository.UserAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserAddressService {

    private final UserAddressRepository userAddressRepository;
    private final UserService userService;

    @Autowired
    public UserAddressService(UserAddressRepository userAddressRepository,
                              UserService userService) {
        this.userAddressRepository = userAddressRepository;
        this.userService = userService;
    }

    public UserAddressDto createAddress(Long userId, UserAddressDto dto) {
        User user = userService.getUserById(userId);

        UserAddress address = new UserAddress();
        address.setUser(user);
        address.setAddrIndex(dto.getAddrIndex());
        address.setAddrCity(dto.getAddrCity());
        address.setAddrStreet(dto.getAddrStreet());
        address.setAddrHouse(dto.getAddrHouse());
        address.setAddrStructure(dto.getAddrStructure());
        address.setAddrApart(dto.getAddrApart());
        address.setDefault(dto.getIsDefault());

        UserAddress saved = userAddressRepository.save(address);
        return convertToDto(saved);
    }

    public UserAddressDto updateAddress(Long addressId, UserAddressDto dto) {
        UserAddress address = userAddressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException("Address not found, id=" + addressId));

        if (dto.getAddrIndex() != null)  address.setAddrIndex(dto.getAddrIndex());
        if (dto.getAddrCity() != null)   address.setAddrCity(dto.getAddrCity());
        if (dto.getAddrStreet() != null) address.setAddrStreet(dto.getAddrStreet());
        if (dto.getAddrHouse() != null)  address.setAddrHouse(dto.getAddrHouse());
        if (dto.getAddrStructure() != null) address.setAddrStructure(dto.getAddrStructure());
        if (dto.getAddrApart() != null)  address.setAddrApart(dto.getAddrApart());
        address.setDefault(dto.getIsDefault());

        UserAddress updated = userAddressRepository.save(address);
        return convertToDto(updated);
    }

    public void deleteAddress(Long addressId) {
        userAddressRepository.deleteById(addressId);
    }

    public List<UserAddressDto> getAllAddressesOfUser(Long userId) {
        return userAddressRepository.findAll().stream()
                .filter(addr -> addr.getUser().getIdUser().equals(userId))
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private UserAddressDto convertToDto(UserAddress address) {
        UserAddressDto dto = new UserAddressDto();
        dto.setIdUserAddress(address.getIdUserAddress());
        dto.setAddrIndex(address.getAddrIndex());
        dto.setAddrCity(address.getAddrCity());
        dto.setAddrStreet(address.getAddrStreet());
        dto.setAddrHouse(address.getAddrHouse());
        dto.setAddrStructure(address.getAddrStructure());
        dto.setAddrApart(address.getAddrApart());
        dto.setDefault(address.isDefault());
        return dto;
    }
}

