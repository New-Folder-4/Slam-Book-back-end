package com.system.slam.service.list;

import com.system.slam.entity.BookLiterary;
import com.system.slam.entity.Status;
import com.system.slam.entity.User;
import com.system.slam.entity.list.OfferList;
import com.system.slam.repository.BookLiteraryRepository;
import com.system.slam.repository.StatusRepository;
import com.system.slam.repository.list.OfferListRepository;
import com.system.slam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OfferListService {

    private final OfferListRepository offerListRepository;
    private final BookLiteraryRepository bookLiteraryRepository;
    private final StatusRepository statusRepository;
    private final UserListService userListService;
    private final UserService userService;

    @Autowired
    public OfferListService(OfferListRepository offerListRepository,
                            BookLiteraryRepository bookLiteraryRepository,
                            StatusRepository statusRepository,
                            UserListService userListService,
                            UserService userService) {
        this.offerListRepository = offerListRepository;
        this.bookLiteraryRepository = bookLiteraryRepository;
        this.statusRepository = statusRepository;
        this.userListService = userListService;
        this.userService = userService;
    }

    public OfferList createOffer(
            Long userId,
            Long bookLiteraryId,
            String isbn,
            LocalDateTime yearPublishing,
            String initialStatus,
            List<Long> categoryIds
    ) {
        BookLiterary bookLit = bookLiteraryRepository.findById(bookLiteraryId)
                .orElseThrow(() -> new RuntimeException("BookLiterary not found, id=" + bookLiteraryId));

        User user = userService.getUserById(userId);

        Status status = statusRepository.findByName(initialStatus)
                .orElseThrow(() -> new RuntimeException("Status not found: " + initialStatus));

        OfferList offer = new OfferList();
        offer.setBookLiterary(bookLit);
        offer.setUser(user);
        offer.setIsbn(isbn);
        offer.setYearPublishing(yearPublishing);
        offer.setCreateAt(LocalDateTime.now());
        offer.setUpdateAt(LocalDateTime.now());
        offer.setStatus(status);
        offer.setCategoryIds(categoryIds);

        OfferList savedOffer = offerListRepository.save(offer);

        userListService.addCategoriesToList(
                savedOffer.getIdOfferList(),
                1,
                categoryIds
        );

        return savedOffer;
    }

    public OfferList updateOffer(
            Long offerId,
            String newIsbn,
            LocalDateTime newYearPublishing,
            String newStatusName,
            List<Long> newCategoryIds
    ) {
        OfferList offer = offerListRepository.findById(offerId)
                .orElseThrow(() -> new RuntimeException("OfferList not found, id=" + offerId));

        if (newIsbn != null) {
            offer.setIsbn(newIsbn);
        }
        if (newYearPublishing != null) {
            offer.setYearPublishing(newYearPublishing);
        }
        if (newStatusName != null) {
            Status st = statusRepository.findByName(newStatusName)
                    .orElseThrow(() -> new RuntimeException("Status not found: " + newStatusName));
            offer.setStatus(st);
        }

        offer.setUpdateAt(LocalDateTime.now());
        offer.setCategoryIds(newCategoryIds);
        OfferList updatedOffer = offerListRepository.save(offer);

        userListService.updateCategoriesForList(
                updatedOffer.getIdOfferList(),
                1,
                newCategoryIds
        );

        return updatedOffer;
    }

    public void deleteOffer(Long offerId, boolean physicalDelete) {
        if (physicalDelete) {
            offerListRepository.deleteById(offerId);
            userListService.deleteByListIdAndType(offerId, 1);
        } else {
            OfferList offer = offerListRepository.findById(offerId)
                    .orElseThrow(() -> new RuntimeException("OfferList not found, id=" + offerId));

            Status deletedStatus = statusRepository.findByName("deleted")
                    .orElseThrow(() -> new RuntimeException("Status 'deleted' not found"));
            offer.setStatus(deletedStatus);
            offer.setUpdateAt(LocalDateTime.now());
            offerListRepository.save(offer);
        }
    }

    public OfferList getOffer(Long offerId) {
        return offerListRepository.findById(offerId)
                .orElseThrow(() -> new RuntimeException("OfferList not found, id=" + offerId));
    }

    public List<OfferList> getAllOffersByUser(Long userId) {
        return offerListRepository.findAll().stream()
                .filter(offer -> offer.getUser().getIdUser().equals(userId))
                .toList();
    }
}
