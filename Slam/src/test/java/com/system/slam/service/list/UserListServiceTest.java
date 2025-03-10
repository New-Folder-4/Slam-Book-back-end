package com.system.slam.service.list;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.system.slam.entity.Category;
import com.system.slam.entity.list.UserList;
import com.system.slam.entity.UserValueCategory;
import com.system.slam.repository.CategoryRepository;
import com.system.slam.repository.UserValueCategoryRepository;
import com.system.slam.repository.list.UserListRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserListServiceTest {

    @Mock
    private UserListRepository userListRepository;

    @Mock
    private UserValueCategoryRepository userValueCategoryRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private UserListService userListService;

    private Long listId = 123L;
    private int typeList = 10;
    private Long categoryId = 456L;

    @BeforeEach
    void setup() {
        // Настройка мокированных объектов
        when(userListRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        when(categoryRepository.findById(categoryId))
                .thenReturn(java.util.Optional.of(new Category()));
    }

    @Test
    void testAddCategoriesToList_Success() {
        // Подготовка
        List<Long> categoryIds = Collections.singletonList(categoryId);

        // Действие
        userListService.addCategoriesToList(listId, typeList, categoryIds);

        // Проверка
        verify(userListRepository).save(any()); // Проверяем, что метод save был вызван
        verify(userValueCategoryRepository).save(any()); // Проверяем, что метод save был вызван
    }

    @Test
    void testUpdateCategoriesForList_Success() {
        // Подготовка
        List<Long> oldCategoryIds = Arrays.asList(789L, 101112L); // Старые категории
        List<Long> newCategoryIds = Arrays.asList(131415L, 161718L); // Новые категории
        List<UserList> userLists = new ArrayList<>();
        userLists.add(new UserList());

        when(userListRepository.findAllByIdListAndTypeList(listId, typeList)).thenReturn(userLists);

        // Действие
        userListService.updateCategoriesForList(listId, typeList, newCategoryIds);

        // Проверка
        verify(userListRepository).deleteAll(userLists); // Проверяем удаление старых списков
        verify(userValueCategoryRepository).deleteAllByUserListIdUserList(any()); // Проверяем удаление категорий
        verify(userListRepository).save(any()); // Проверяем создание новых списков
        verify(userValueCategoryRepository).save(any()); // Проверяем добавление новых категорий
    }

    @Test
    void testDeleteByListIdAndType_Success() {
        // Подготовка
        List<UserList> userLists = new ArrayList<>();
        userLists.add(new UserList());

        when(userListRepository.findAllByIdListAndTypeList(listId, typeList)).thenReturn(userLists);

        // Действие
        userListService.deleteByListIdAndType(listId, typeList);

        // Проверка
        verify(userListRepository).deleteAll(userLists); // Проверяем удаление списков
        verify(userValueCategoryRepository).deleteAllByUserListIdUserList(any()); // Проверяем удаление категорий
    }
}