package com.system.slam.service.list;

import com.system.slam.entity.Category;
import com.system.slam.entity.UserValueCategory;
import com.system.slam.entity.list.UserList;
import com.system.slam.repository.CategoryRepository;
import com.system.slam.repository.UserValueCategoryRepository;
import com.system.slam.repository.list.UserListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserListService {

    private final UserListRepository userListRepository;
    private final UserValueCategoryRepository userValueCategoryRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public UserListService(UserListRepository userListRepository,
                           UserValueCategoryRepository userValueCategoryRepository,
                           CategoryRepository categoryRepository) {
        this.userListRepository = userListRepository;
        this.userValueCategoryRepository = userValueCategoryRepository;
        this.categoryRepository = categoryRepository;
    }

    public void addCategoriesToList(Long idList, int typeList, List<Long> categoryIds) {
        UserList userList = new UserList();
        userList.setTypeList(typeList);
        userList.setIdList(idList);
        userList = userListRepository.save(userList);

        if (categoryIds != null) {
            for (Long catId : categoryIds) {
                Category category = categoryRepository.findById(catId)
                        .orElseThrow(() -> new RuntimeException("Category not found, id=" + catId));

                UserValueCategory uvc = new UserValueCategory();
                uvc.setUserList(userList);
                uvc.setCategory(category);
                userValueCategoryRepository.save(uvc);
            }
        }
    }

    public void updateCategoriesForList(Long idList, int typeList, List<Long> newCategoryIds) {
        List<UserList> userLists = userListRepository.findAllByIdListAndTypeList(idList, typeList);

        for (UserList ul : userLists) {
            userValueCategoryRepository.deleteAllByIdUserList(ul.getIdUserList());
        }
        userListRepository.deleteAll(userLists);
        addCategoriesToList(idList, typeList, newCategoryIds);
    }

    public void deleteByListIdAndType(Long idList, int typeList) {
        List<UserList> userLists = userListRepository.findAllByIdListAndTypeList(idList, typeList);
        for (UserList ul : userLists) {
            userValueCategoryRepository.deleteAllByIdUserList(ul.getIdUserList());
        }
        userListRepository.deleteAll(userLists);
    }
}

