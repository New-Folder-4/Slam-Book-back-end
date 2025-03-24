package com.system.slam.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.system.slam.entity.User;
import com.system.slam.entity.UserAddress;
import com.system.slam.repository.UserAddressRepository;
import com.system.slam.service.UserAddressService;
import com.system.slam.service.UserService;
import com.system.slam.web.dto.UserAddressDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserAddressServiceTest {

    @Mock
    private UserAddressRepository userAddressRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserAddressService userAddressService;

    private User user;
    private UserAddress userAddress;
    private UserAddressDto userAddressDto;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setIdUser(1L);

        userAddress = new UserAddress();
        userAddress.setIdUserAddress(1L);
        userAddress.setUser(user);
        userAddress.setAddrIndex("12345");
        userAddress.setAddrCity("City");
        userAddress.setAddrStreet("Street");
        userAddress.setAddrHouse("House");
        userAddress.setAddrStructure("Structure");
        userAddress.setAddrApart("Apartment");
        userAddress.setDefault(true);

        userAddressDto = new UserAddressDto();
        userAddressDto.setAddrIndex("12345");
        userAddressDto.setAddrCity("City");
        userAddressDto.setAddrStreet("Street");
        userAddressDto.setAddrHouse("House");
        userAddressDto.setAddrStructure("Structure");
        userAddressDto.setAddrApart("Apartment");
        userAddressDto.setDefault(true);
    }

    @Test
    void createAddress_Success() {
        // Arrange
        when(userService.getUserById(1L)).thenReturn(user);
        when(userAddressRepository.save(any(UserAddress.class))).thenReturn(userAddress);

        // Act
        UserAddressDto result = userAddressService.createAddress(1L, userAddressDto);

        // Assert
        assertNotNull(result);
        assertEquals(userAddress.getIdUserAddress(), result.getIdUserAddress());
        assertEquals(userAddress.getAddrIndex(), result.getAddrIndex());

        verify(userService, times(1)).getUserById(1L);
        verify(userAddressRepository, times(1)).save(any(UserAddress.class));
    }

    @Test
    void updateAddress_Success() {
        // Arrange
        when(userAddressRepository.findById(1L)).thenReturn(Optional.of(userAddress));
        when(userAddressRepository.save(any(UserAddress.class))).thenReturn(userAddress);

        // Act
        UserAddressDto result = userAddressService.updateAddress(1L, userAddressDto);

        // Assert
        assertNotNull(result);
        assertEquals(userAddress.getIdUserAddress(), result.getIdUserAddress());
        assertEquals(userAddress.getAddrIndex(), result.getAddrIndex());

        verify(userAddressRepository, times(1)).findById(1L);
        verify(userAddressRepository, times(1)).save(any(UserAddress.class));
    }

    @Test
    void deleteAddress_Success() {
        // Act
        userAddressService.deleteAddress(1L);

        // Assert
        verify(userAddressRepository, times(1)).deleteById(1L);
    }

    @Test
    void getAllAddressesOfUser_Success() {
        // Arrange
        when(userAddressRepository.findAll()).thenReturn(Collections.singletonList(userAddress));

        // Act
        List<UserAddressDto> result = userAddressService.getAllAddressesOfUser(1L);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(userAddress.getIdUserAddress(), result.get(0).getIdUserAddress());

        verify(userAddressRepository, times(1)).findAll();
    }
}
