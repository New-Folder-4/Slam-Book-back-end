package com.system.slam.repository;

import com.system.slam.entity.BookResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<BookResponse, Long> {
    List<BookResponse> findAllByBookLiterary_IdBookLiterary(Long bookLiteraryId);
    List<BookResponse> findAllByUser_IdUser(Long userId);
}
