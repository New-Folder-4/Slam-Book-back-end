package com.system.slam.repository;

import com.system.slam.entity.Author;
import com.system.slam.repository.custom.CustomAuthorRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>, CustomAuthorRepository {

    Optional<Author> findByFirstNameAndLastName(String firstName, String lastName);
}
