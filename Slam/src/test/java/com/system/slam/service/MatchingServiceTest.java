package com.system.slam.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.system.slam.entity.list.OfferList;
import com.system.slam.entity.list.WishList;
import com.system.slam.repository.list.OfferListRepository;
import com.system.slam.repository.list.WishListRepository;
import com.system.slam.service.MatchingService;
import com.system.slam.service.list.UserListService;
import com.system.slam.web.dto.ExchangeMatchDto;
import com.system.slam.web.dto.OfferDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Method;
import java.util.*;

@ExtendWith(MockitoExtension.class)
public class MatchingServiceTest {

    @Mock
    private WishListRepository wishListRepository;

    @Mock
    private OfferListRepository offerListRepository;

    @Mock
    private UserListService userListService;

    @InjectMocks
    private MatchingService matchingService;

    private WishList wishList;
    private OfferList offerList;

    @BeforeEach
    void setUp() {
        wishList = new WishList();
        wishList.setIdWishList(1L);

        offerList = new OfferList();
        offerList.setIdOfferList(1L);
    }

    @Test
    void findMatchesForUser_FullMatchFound() {
        // Arrange
        Long userId = 1L;
        when(wishListRepository.findAll()).thenReturn(List.of(wishList));
        when(offerListRepository.findAll()).thenReturn(List.of(offerList));

        Set<Long> wishCategoryIds = Set.of(1L, 2L);
        Set<Long> offerCategoryIds = Set.of(1L, 2L, 3L);

        when(userListService.getCategoryIdsForList(wishList.getIdWishList(), 2)).thenReturn(new ArrayList<>(wishCategoryIds));
        when(userListService.getCategoryIdsForList(offerList.getIdOfferList(), 1)).thenReturn(new ArrayList<>(offerCategoryIds));

        // Act
        List<ExchangeMatchDto> result = matchingService.findMatchesForUser(userId);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("full match", result.get(0).getMatchType());
        assertEquals(1, result.get(0).getOffers().size());

        verify(wishListRepository, times(1)).findAll();
        verify(offerListRepository, times(1)).findAll();
        verify(userListService, times(1)).getCategoryIdsForList(wishList.getIdWishList(), 2);
        verify(userListService, times(1)).getCategoryIdsForList(offerList.getIdOfferList(), 1);
    }

    @Test
    void findMatchesForUser_PartialMatchFound() {
        // Arrange
        Long userId = 1L;
        when(wishListRepository.findAll()).thenReturn(List.of(wishList));
        when(offerListRepository.findAll()).thenReturn(List.of(offerList));

        Set<Long> wishCategoryIds = Set.of(1L, 2L);
        Set<Long> offerCategoryIds = Set.of(2L, 3L);

        when(userListService.getCategoryIdsForList(wishList.getIdWishList(), 2)).thenReturn(new ArrayList<>(wishCategoryIds));
        when(userListService.getCategoryIdsForList(offerList.getIdOfferList(), 1)).thenReturn(new ArrayList<>(offerCategoryIds));

        // Act
        List<ExchangeMatchDto> result = matchingService.findMatchesForUser(userId);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("partial match", result.get(0).getMatchType());
        assertEquals(1, result.get(0).getOffers().size());

        verify(wishListRepository, times(1)).findAll();
        verify(offerListRepository, times(1)).findAll();
        verify(userListService, times(1)).getCategoryIdsForList(wishList.getIdWishList(), 2);
        verify(userListService, times(1)).getCategoryIdsForList(offerList.getIdOfferList(), 1);
    }

    @Test
    void findMatchesForUser_NoMatchesFound() {
        // Arrange
        Long userId = 1L;
        when(wishListRepository.findAll()).thenReturn(List.of(wishList));
        when(offerListRepository.findAll()).thenReturn(List.of(offerList));

        Set<Long> wishCategoryIds = Set.of(1L, 2L);
        Set<Long> offerCategoryIds = Set.of(3L, 4L);

        when(userListService.getCategoryIdsForList(wishList.getIdWishList(), 2)).thenReturn(new ArrayList<>(wishCategoryIds));
        when(userListService.getCategoryIdsForList(offerList.getIdOfferList(), 1)).thenReturn(new ArrayList<>(offerCategoryIds));

        // Act
        List<ExchangeMatchDto> result = matchingService.findMatchesForUser(userId);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(wishListRepository, times(1)).findAll();
        verify(offerListRepository, times(1)).findAll();
        verify(userListService, times(1)).getCategoryIdsForList(wishList.getIdWishList(), 2);
        verify(userListService, times(1)).getCategoryIdsForList(offerList.getIdOfferList(), 1);
    }

    @Test
    void getIntersection_NonEmptyIntersection() throws Exception {
        // Arrange
        Set<Long> set1 = Set.of(1L, 2L, 3L);
        Set<Long> set2 = Set.of(2L, 3L, 4L);

        // Act
        Method method = MatchingService.class.getDeclaredMethod("getIntersection", Set.class, Set.class);
        method.setAccessible(true);
        Set<Long> intersection = (Set<Long>) method.invoke(matchingService, set1, set2);

        // Assert
        assertEquals(2, intersection.size());
        assertTrue(intersection.contains(2L));
        assertTrue(intersection.contains(3L));
    }

    @Test
    void getIntersection_EmptyIntersection() throws Exception {
        // Arrange
        Set<Long> set1 = Set.of(1L, 2L);
        Set<Long> set2 = Set.of(3L, 4L);

        // Act
        Method method = MatchingService.class.getDeclaredMethod("getIntersection", Set.class, Set.class);
        method.setAccessible(true);
        Set<Long> intersection = (Set<Long>) method.invoke(matchingService, set1, set2);

        // Assert
        assertTrue(intersection.isEmpty());
    }

    @Test
    void convertOfferToDto_Success() throws Exception {
        // Arrange
        when(userListService.getCategoryIdsForList(offerList.getIdOfferList(), 1)).thenReturn(List.of(1L, 2L));

        // Act
        Method method = MatchingService.class.getDeclaredMethod("convertOfferToDto", OfferList.class);
        method.setAccessible(true);
        OfferDto dto = (OfferDto) method.invoke(matchingService, offerList);

        // Assert
        assertNotNull(dto);
        assertEquals(offerList.getIdOfferList(), dto.getIdOfferList());
        assertEquals(2, dto.getCategoryIds().size());
        assertTrue(dto.getCategoryIds().contains(1L));
        assertTrue(dto.getCategoryIds().contains(2L));

        verify(userListService, times(1)).getCategoryIdsForList(offerList.getIdOfferList(), 1);
    }
}
