package com.system.slam.service;

import com.system.slam.web.dto.AuthorDto;
import com.system.slam.entity.Author;
import com.system.slam.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public AuthorDto createAuthorDto(String firstName, String lastName) {
        Author author = createAuthor(firstName, lastName);
        return convertToDto(author);
    }

    public Author getAuthor(Long authorId) {
        return authorRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("Author not found, id=" + authorId));
    }

    public AuthorDto getAuthorDtoById(Long authorId) {
        Author author = getAuthor(authorId);
        return convertToDto(author);
    }

    public Author updateAuthor(Long authorId,
                               String newFirstName,
                               String newLastName) {
        Author author = getAuthor(authorId);
        author.setFirstName(newFirstName);
        author.setLastName(newLastName);
        return authorRepository.save(author);
    }

    public AuthorDto updateAuthorDto(Long authorId, String newFirstName, String newLastName) {
        Author author = updateAuthor(authorId, newFirstName, newLastName);
        return convertToDto(author);
    }

    public void deleteAuthor(Long authorId) {
        authorRepository.deleteById(authorId);
    }

    public Optional<Author> findByFirstNameAndLastName(String firstName, String lastName) {
        return authorRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    public List<AuthorDto> getAllAuthorsDto() {
        return authorRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private AuthorDto convertToDto(Author author) {
        return new AuthorDto(
                author.getIdAuthor(),
                author.getFirstName(),
                author.getLastName());
    }
}


