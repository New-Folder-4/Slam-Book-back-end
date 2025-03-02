package com.system.slam.service;

import com.system.slam.entity.BookLiterary;
import com.system.slam.entity.Status;
import com.system.slam.entity.User;
import com.system.slam.entity.list.OfferList;
import com.system.slam.repository.BookLiteraryRepository;
import com.system.slam.repository.StatusRepository;
import com.system.slam.repository.list.OfferListRepository;
import com.system.slam.service.list.UserListService;
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

class OfferServiceTest {

    @Mock
    private OfferListRepository offerListRepository;

    @Mock
    private BookLiteraryRepository bookLiteraryRepository;

    @Mock
    private StatusRepository statusRepository;

    @Mock
    private UserListService userListService;

    @Mock
    private UserService userService;

    @InjectMocks
    private OfferService offerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateOffer() {
        Long userId = 1L;
        Long bookLiteraryId = 1L;
        String isbn = "123-456";
        LocalDateTime yearPublishing = LocalDateTime.now();
        String initialStatus = "active";
        List<Long> categoryIds = List.of(1L, 2L);

        BookLiterary bookLit = new BookLiterary();
        User user = new User();
        Status status = new Status();
        OfferList offer = new OfferList();

        when(bookLiteraryRepository.findById(bookLiteraryId)).thenReturn(Optional.of(bookLit));
        when(userService.getUserById(userId)).thenReturn(user);
        when(statusRepository.findByName(initialStatus)).thenReturn(Optional.of(status));
        when(offerListRepository.save(any(OfferList.class))).thenReturn(offer);

        OfferList result = offerService.createOffer(userId, bookLiteraryId, isbn, yearPublishing, initialStatus, categoryIds);

        assertNotNull(result);
        assertEquals(isbn, result.getIsbn());
        assertEquals(yearPublishing, result.getYearPublishing());
        verify(userListService, times(1)).addCategoriesToList(offer.getIdOfferList(), 1, categoryIds);
    }

    @Test
    void testUpdateOffer() {
        Long offerId = 1L;
        String newIsbn = "654-321";
        LocalDateTime newYearPublishing = LocalDateTime.now();
        String newStatusName = "inactive";
        List<Long> newCategoryIds = List.of(3L, 4L);

        OfferList offer = new OfferList();
        Status status = new Status();

        when(offerListRepository.findById(offerId)).thenReturn(Optional.of(offer));
        when(statusRepository.findByName(newStatusName)).thenReturn(Optional.of(status));
        when(offerListRepository.save(any(OfferList.class))).thenReturn(offer);

        OfferList result = offerService.updateOffer(offerId, newIsbn, newYearPublishing, newStatusName, newCategoryIds);

        assertNotNull(result);
        assertEquals(newIsbn, result.getIsbn());
        assertEquals(newYearPublishing, result.getYearPublishing());
        verify(userListService, times(1)).updateCategoriesForList(offer.getIdOfferList(), 1, newCategoryIds);
    }

    @Test
    void testDeleteOffer_PhysicalDelete() {
        Long offerId = 1L;

        offerService.deleteOffer(offerId, true);

        verify(offerListRepository, times(1)).deleteById(offerId);
        verify(userListService, times(1)).deleteByListIdAndType(offerId, 1);
    }

    @Test
    void testDeleteOffer_LogicalDelete() {
        Long offerId = 1L;
        OfferList offer = new OfferList();
        Status deletedStatus = new Status();

        when(offerListRepository.findById(offerId)).thenReturn(Optional.of(offer));
        when(statusRepository.findByName("deleted")).thenReturn(Optional.of(deletedStatus));
        when(offerListRepository.save(any(OfferList.class))).thenReturn(offer);

        offerService.deleteOffer(offerId, false);

        assertEquals(deletedStatus, offer.getStatus());
        verify(offerListRepository, times(1)).save(offer);
    }

    @Test
    void testGetOffer() {
        Long offerId = 1L;
        OfferList offer = new OfferList();

        when(offerListRepository.findById(offerId)).thenReturn(Optional.of(offer));

        OfferList result = offerService.getOffer(offerId);

        assertNotNull(result);
    }

    @Test
    void testGetAllOffersByUser() {
        Long userId = 1L;
        User user = new User();
        OfferList offer1 = new OfferList();
        offer1.setUser(user);
        OfferList offer2 = new OfferList();
        offer2.setUser(user);

        when(offerListRepository.findAll()).thenReturn(List.of(offer1, offer2));

        List<OfferList> result = offerService.getAllOffersByUser(userId);

        assertEquals(2, result.size());
    }
}
