package com.system.slam.repository;

import com.system.slam.entity.UserValueCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserValueCategoryRepository extends JpaRepository<UserValueCategory, Long> {
}
