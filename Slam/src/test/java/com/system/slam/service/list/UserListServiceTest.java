package com.system.slam.service.list;

import com.system.slam.entity.Category;
import com.system.slam.entity.UserValueCategory;
import com.system.slam.entity.list.UserList;
import com.system.slam.repository.CategoryRepository;
import com.system.slam.repository.UserValueCategoryRepository;
import com.system.slam.repository.list.UserListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class UserListServiceTest {

    @Mock
    private UserListRepository userListRepository;

    @Mock
    private UserValueCategoryRepository userValueCategoryRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private UserListService userListService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddCategoriesToList() {
        Long idList = 1L;
        int typeList = 1;
        List<Long> categoryIds = List.of(1L, 2L);

        UserList userList = new UserList();
        userList.setIdList(idList);
        userList.setTypeList(typeList);

        Category category1 = new Category();
        Category category2 = new Category();

        when(userListRepository.save(any(UserList.class))).thenReturn(userList);
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category1));
        when(categoryRepository.findById(2L)).thenReturn(Optional.of(category2));

        userListService.addCategoriesToList(idList, typeList, categoryIds);

        verify(userListRepository, times(1)).save(any(UserList.class));
        verify(userValueCategoryRepository, times(2)).save(any(UserValueCategory.class));
    }

    @Test
    void testUpdateCategoriesForList() {
        Long idList = 1L;
        int typeList = 1;
        List<Long> newCategoryIds = List.of(3L, 4L);

        UserList userList = new UserList();
        userList.setIdList(idList);
        userList.setTypeList(typeList);

        when(userListRepository.findAllByIdListAndTypeList(idList, typeList)).thenReturn(List.of(userList));

        userListService.updateCategoriesForList(idList, typeList, newCategoryIds);

        verify(userValueCategoryRepository, times(1)).deleteAllByIdUserList(userList.getIdUserList());
        verify(userListRepository, times(1)).deleteAll(List.of(userList));
        verify(userListRepository, times(1)).save(any(UserList.class));
        verify(userValueCategoryRepository, times(2)).save(any(UserValueCategory.class));
    }

    @Test
    void testDeleteByListIdAndType() {
        Long idList = 1L;
        int typeList = 1;

        UserList userList = new UserList();
        userList.setIdList(idList);
        userList.setTypeList(typeList);

        when(userListRepository.findAllByIdListAndTypeList(idList, typeList)).thenReturn(List.of(userList));

        userListService.deleteByListIdAndType(idList, typeList);

        verify(userValueCategoryRepository, times(1)).deleteAllByIdUserList(userList.getIdUserList());
        verify(userListRepository, times(1)).deleteAll(List.of(userList));
    }
}
