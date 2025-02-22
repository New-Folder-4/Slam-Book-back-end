package com.system.slam.repository;


import com.system.slam.entity.BookLiterary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookLiteraryRepository extends JpaRepository<BookLiterary, Long> {
}
