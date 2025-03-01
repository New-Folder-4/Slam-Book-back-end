package com.system.slam.service.list;

import com.system.slam.entity.Status;
import com.system.slam.entity.User;
import com.system.slam.entity.UserAddress;
import com.system.slam.entity.list.WishList;
import com.system.slam.repository.StatusRepository;
import com.system.slam.repository.UserAddressRepository;
import com.system.slam.repository.list.WishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WishListService {

    private final WishListRepository wishListRepository;
    private final StatusRepository statusRepository;
    private final UserAddressRepository userAddressRepository;
    private final UserListService userListService;

    public WishList createWish(Long userId, Long addressId, String initialStatus, List<Long> categoryIds) {
        WishList wish = new WishList();
        wish.setUser(new User(userId));
        wish.setUserAddress(new UserAddress(addressId));

        Status status = statusRepository.findByName(initialStatus)
                .orElseThrow(() -> new RuntimeException("Status not found: " + initialStatus));
        wish.setStatus(status);

        wish.setCreateAt(LocalDateTime.now());
        wish.setUpdateAt(LocalDateTime.now());

        WishList savedWish = wishListRepository.save(wish);
        userListService.addCategoriesToList(savedWish.getIdWishList(), 2, categoryIds);

        return savedWish;
    }

    public WishList updateWish(Long wishId, Long newAddressId, String newStatusName, List<Long> newCategoryIds) {
        WishList wish = wishListRepository.findById(wishId)
                .orElseThrow(() -> new RuntimeException("WishList not found, id=" + wishId));

        if (newAddressId != null) {
            UserAddress addr = new UserAddress(newAddressId);
            wish.setUserAddress(addr);
        }

        if (newStatusName != null) {
            Status newStatus = statusRepository.findByName(newStatusName)
                    .orElseThrow(() -> new RuntimeException("Status not found: " + newStatusName));
            wish.setStatus(newStatus);
        }

        wish.setUpdateAt(LocalDateTime.now());
        WishList updatedWish = wishListRepository.save(wish);

        userListService.updateCategoriesForList(updatedWish.getIdWishList(), 2, newCategoryIds);

        return updatedWish;
    }

    public void deleteWish(Long wishId, boolean physicalDelete) {
        if (physicalDelete) {
            wishListRepository.deleteById(wishId);
            userListService.deleteByListIdAndType(wishId, 2);
        } else {
            WishList wish = wishListRepository.findById(wishId)
                    .orElseThrow(() -> new RuntimeException("WishList not found, id=" + wishId));

            Status deletedStatus = statusRepository.findByName("deleted")
                    .orElseThrow(() -> new RuntimeException("Status not found: deleted"));
            wish.setStatus(deletedStatus);
            wish.setUpdateAt(LocalDateTime.now());
            wishListRepository.save(wish);
        }
    }
}

