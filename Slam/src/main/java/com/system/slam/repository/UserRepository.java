package com.system.slam.repository;

import com.system.slam.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<Object> findByUserName(String username);

    Optional<Object> findByEmail(String email);
}
