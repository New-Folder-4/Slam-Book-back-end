package com.system.slam.repository;

import com.system.slam.entity.UserValueCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserValueCategoryRepository extends JpaRepository<UserValueCategory, Long> {
    void deleteAllByUserListIdUserList(Long id);

    List<UserValueCategory> findAllByUserList_IdUserList(Long idUserList);


}
