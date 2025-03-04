package com.system.slam.service;

import com.system.slam.entity.Author;
import com.system.slam.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author createAuthor(String firstName, String lastName) {
        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);
        return authorRepository.save(author);
    }

    public Author getAuthor(Long authorId) {
        return authorRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("Author not found, id=" + authorId));
    }

    public Author updateAuthor(Long authorId, String newFirstName, String newLastName) {
        Author author = getAuthor(authorId);
        author.setFirstName(newFirstName);
        author.setLastName(newLastName);
        return authorRepository.save(author);
    }

    public void deleteAuthor(Long authorId) {
        authorRepository.deleteById(authorId);
    }

    public Optional<Author> findByFirstNameAndLastName(String firstName, String lastName) {
        return authorRepository.findByFirstNameAndLastName(firstName, lastName);
    }
}

