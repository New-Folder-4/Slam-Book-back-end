package com.system.slam.repository;

import com.system.slam.entity.BookResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookResponseRepository extends JpaRepository<BookResponse, Long> {
}
