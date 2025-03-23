package com.system.slam.repository;

import com.system.slam.entity.Status;
import com.system.slam.repository.custom.CustomStatusRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long>, CustomStatusRepository {
    Optional<Status> findByName(String name);
}
