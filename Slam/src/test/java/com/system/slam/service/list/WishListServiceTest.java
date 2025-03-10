package com.system.slam.service.list;

import com.system.slam.entity.Status;
import com.system.slam.entity.User;
import com.system.slam.entity.UserAddress;
import com.system.slam.entity.list.WishList;
import com.system.slam.repository.StatusRepository;
import com.system.slam.repository.list.WishListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class WishListServiceTest {

    @Mock
    private WishListRepository wishListRepository;

    @Mock
    private StatusRepository statusRepository;

    @Mock
    private UserListService userListService;

    @InjectMocks
    private WishListService wishListService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateWish() {
        Long userId = 1L;
        Long addressId = 2L;
        String initialStatus = "active";
        List<Long> categoryIds = Arrays.asList(3L, 4L);

        Status status = new Status();
        status.setName(initialStatus);

        WishList wishList = new WishList();
        wishList.setIdWishList(1L);
        wishList.setUser(new User(userId));
        wishList.setUserAddress(new UserAddress(addressId));
        wishList.setStatus(status);
        wishList.setCreateAt(LocalDateTime.now());
        wishList.setUpdateAt(LocalDateTime.now());

        when(statusRepository.findByName(initialStatus)).thenReturn(Optional.of(status));
        when(wishListRepository.save(any(WishList.class))).thenReturn(wishList);

        WishList result = wishListService.createWish(userId, addressId, initialStatus, categoryIds);

        assertNotNull(result);
        assertEquals(userId, result.getUser().getIdUser());
        assertEquals(addressId, result.getUserAddress().getIdUserAddress());
        assertEquals(initialStatus, result.getStatus().getName());
        verify(userListService).addCategoriesToList(result.getIdWishList(), 2, categoryIds);
    }

    @Test
    public void testUpdateWish() {
        Long wishId = 1L;
        Long newAddressId = 3L;
        String newStatusName = "inactive";
        List<Long> newCategoryIds = Arrays.asList(5L, 6L);

        WishList existingWish = new WishList();
        existingWish.setIdWishList(wishId);

        Status newStatus = new Status();
        newStatus.setName(newStatusName);

        when(wishListRepository.findById(wishId)).thenReturn(Optional.of(existingWish));
        when(statusRepository.findByName(newStatusName)).thenReturn(Optional.of(newStatus));
        when(wishListRepository.save(any(WishList.class))).thenReturn(existingWish);

        WishList result = wishListService.updateWish(wishId, newAddressId, newStatusName, newCategoryIds);

        assertNotNull(result);
        assertEquals(newAddressId, result.getUserAddress().getIdUserAddress());
        assertEquals(newStatusName, result.getStatus().getName());
        verify(userListService).updateCategoriesForList(wishId, 2, newCategoryIds);
    }

    @Test
    public void testDeleteWishPhysical() {
        Long wishId = 1L;

        wishListService.deleteWish(wishId, true);

        verify(wishListRepository).deleteById(wishId);
        verify(userListService).deleteByListIdAndType(wishId, 2);
    }

    @Test
    public void testDeleteWishLogical() {
        Long wishId = 1L;

        WishList wish = new WishList();
        wish.setIdWishList(wishId);

        Status deletedStatus = new Status();
        deletedStatus.setName("deleted");

        when(wishListRepository.findById(wishId)).thenReturn(Optional.of(wish));
        when(statusRepository.findByName("deleted")).thenReturn(Optional.of(deletedStatus));
        when(wishListRepository.save(any(WishList.class))).thenReturn(wish);

        wishListService.deleteWish(wishId, false);

        assertEquals("deleted", wish.getStatus().getName());
        verify(wishListRepository).save(wish);
    }
}
