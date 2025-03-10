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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class OfferServiceTest {

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
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateOffer() {
        Long userId = 1L;
        Long bookLiteraryId = 2L;
        String isbn = "123-456-789";
        LocalDateTime yearPublishing = LocalDateTime.of(2023, 1, 1, 0, 0);
        String initialStatus = "active";
        List<Long> categoryIds = Arrays.asList(3L, 4L);

        BookLiterary bookLit = new BookLiterary();
        bookLit.setIdBookLiterary(bookLiteraryId);

        User user = new User();
        user.setIdUser(userId);

        Status status = new Status();
        status.setName(initialStatus);

        OfferList offer = new OfferList();
        offer.setIdOfferList(1L);
        offer.setBookLiterary(bookLit);
        offer.setUser(user);
        offer.setIsbn(isbn);
        offer.setYearPublishing(yearPublishing);
        offer.setCreateAt(LocalDateTime.now());
        offer.setUpdateAt(LocalDateTime.now());
        offer.setStatus(status);

        when(bookLiteraryRepository.findById(bookLiteraryId)).thenReturn(Optional.of(bookLit));
        when(userService.getUserById(userId)).thenReturn(user);
        when(statusRepository.findByName(initialStatus)).thenReturn(Optional.of(status));
        when(offerListRepository.save(any(OfferList.class))).thenReturn(offer);

        OfferList result = offerService.createOffer(userId, bookLiteraryId, isbn, yearPublishing, initialStatus, categoryIds);

        assertNotNull(result);
        assertEquals(isbn, result.getIsbn());
        assertEquals(yearPublishing, result.getYearPublishing());
        assertEquals(initialStatus, result.getStatus().getName());
        verify(userListService).addCategoriesToList(result.getIdOfferList(), 1, categoryIds);
    }

    @Test
    public void testUpdateOffer() {
        Long offerId = 1L;
        String newIsbn = "987-654-321";
        LocalDateTime newYearPublishing = LocalDateTime.of(2024, 1, 1, 0, 0);
        String newStatusName = "inactive";
        List<Long> newCategoryIds = Arrays.asList(5L, 6L);

        OfferList offer = new OfferList();
        offer.setIdOfferList(offerId);

        Status newStatus = new Status();
        newStatus.setName(newStatusName);

        when(offerListRepository.findById(offerId)).thenReturn(Optional.of(offer));
        when(statusRepository.findByName(newStatusName)).thenReturn(Optional.of(newStatus));
        when(offerListRepository.save(any(OfferList.class))).thenReturn(offer);

        OfferList result = offerService.updateOffer(offerId, newIsbn, newYearPublishing, newStatusName, newCategoryIds);

        assertNotNull(result);
        assertEquals(newIsbn, result.getIsbn());
        assertEquals(newYearPublishing, result.getYearPublishing());
        assertEquals(newStatusName, result.getStatus().getName());
        verify(userListService).updateCategoriesForList(offerId, 1, newCategoryIds);
    }

    @Test
    public void testDeleteOfferPhysical() {
        Long offerId = 1L;

        offerService.deleteOffer(offerId, true);

        verify(offerListRepository).deleteById(offerId);
        verify(userListService).deleteByListIdAndType(offerId, 1);
    }

    @Test
    public void testDeleteOfferLogical() {
        Long offerId = 1L;

        OfferList offer = new OfferList();
        offer.setIdOfferList(offerId);

        Status deletedStatus = new Status();
        deletedStatus.setName("deleted");

        when(offerListRepository.findById(offerId)).thenReturn(Optional.of(offer));
        when(statusRepository.findByName("deleted")).thenReturn(Optional.of(deletedStatus));
        when(offerListRepository.save(any(OfferList.class))).thenReturn(offer);

        offerService.deleteOffer(offerId, false);

        assertEquals("deleted", offer.getStatus().getName());
        verify(offerListRepository).save(offer);
    }

    @Test
    public void testGetOffer() {
        Long offerId = 1L;

        OfferList offer = new OfferList();
        offer.setIdOfferList(offerId);

        when(offerListRepository.findById(offerId)).thenReturn(Optional.of(offer));

        OfferList result = offerService.getOffer(offerId);

        assertNotNull(result);
        assertEquals(offerId, result.getIdOfferList());
    }

    @Test
    public void testGetAllOffersByUser() {
        Long userId = 1L;

        User user = new User();
        user.setIdUser(userId);

        OfferList offer1 = new OfferList();
        offer1.setUser(user);

        OfferList offer2 = new OfferList();
        offer2.setUser(user);

        when(offerListRepository.findAll()).thenReturn(Arrays.asList(offer1, offer2));

        List<OfferList> result = offerService.getAllOffersByUser(userId);

        assertEquals(2, result.size());
        assertTrue(result.stream().allMatch(offer -> offer.getUser().getIdUser().equals(userId)));
    }
}
