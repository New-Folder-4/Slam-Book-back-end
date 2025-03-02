package com.system.slam.service.list;

import com.system.slam.entity.Status;
import com.system.slam.entity.User;
import com.system.slam.entity.UserAddress;
import com.system.slam.entity.list.WishList;
import com.system.slam.repository.StatusRepository;
import com.system.slam.repository.UserAddressRepository;
import com.system.slam.repository.list.WishListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WishListServiceTest {

    @Mock
    private WishListRepository wishListRepository;

    @Mock
    private StatusRepository statusRepository;

    @Mock
    private UserAddressRepository userAddressRepository;

    @Mock
    private UserListService userListService;

    @InjectMocks
    private WishListService wishListService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateWish() {
        Long userId = 1L;
        Long addressId = 1L;
        String initialStatus = "active";
        List<Long> categoryIds = List.of(1L, 2L);

        User user = new User(userId);
        UserAddress userAddress = new UserAddress(addressId);
        Status status = new Status();
        WishList wish = new WishList();

        when(statusRepository.findByName(initialStatus)).thenReturn(Optional.of(status));
        when(wishListRepository.save(any(WishList.class))).thenReturn(wish);

        WishList result = wishListService.createWish(userId, addressId, initialStatus, categoryIds);

        assertNotNull(result);
        assertEquals(user, result.getUser());
        assertEquals(userAddress, result.getUserAddress());
        assertEquals(status, result.getStatus());
        verify(userListService, times(1)).addCategoriesToList(wish.getIdWishList(), 2, categoryIds);
    }

    @Test
    void testUpdateWish() {
        Long wishId = 1L;
        Long newAddressId = 2L;
        String newStatusName = "inactive";
        List<Long> newCategoryIds = List.of(3L, 4L);

        WishList wish = new WishList();
        Status newStatus = new Status();

        when(wishListRepository.findById(wishId)).thenReturn(Optional.of(wish));
        when(statusRepository.findByName(newStatusName)).thenReturn(Optional.of(newStatus));
        when(wishListRepository.save(any(WishList.class))).thenReturn(wish);

        WishList result = wishListService.updateWish(wishId, newAddressId, newStatusName, newCategoryIds);

        assertNotNull(result);
        assertEquals(newStatus, result.getStatus());
        verify(userListService, times(1)).updateCategoriesForList(wish.getIdWishList(), 2, newCategoryIds);
    }

    @Test
    void testDeleteWish_PhysicalDelete() {
        Long wishId = 1L;

        wishListService.deleteWish(wishId, true);

        verify(wishListRepository, times(1)).deleteById(wishId);
        verify(userListService, times(1)).deleteByListIdAndType(wishId, 2);
    }

    @Test
    void testDeleteWish_LogicalDelete() {
        Long wishId = 1L;
        WishList wish = new WishList();
        Status deletedStatus = new Status();

        when(wishListRepository.findById(wishId)).thenReturn(Optional.of(wish));
        when(statusRepository.findByName("deleted")).thenReturn(Optional.of(deletedStatus));
        when(wishListRepository.save(any(WishList.class))).thenReturn(wish);

        wishListService.deleteWish(wishId, false);

        assertEquals(deletedStatus, wish.getStatus());
        verify(wishListRepository, times(1)).save(wish);
    }
}
