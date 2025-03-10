package com.system.slam.service;

import com.system.slam.entity.Author;
import com.system.slam.repository.AuthorRepository;
import com.system.slam.web.dto.AuthorDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorService authorService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateAuthorDto() {
        String firstName = "John";
        String lastName = "Doe";

        Author author = new Author();
        author.setIdAuthor(1L);
        author.setFirstName(firstName);
        author.setLastName(lastName);

        when(authorRepository.save(any(Author.class))).thenReturn(author);

        AuthorDto result = authorService.createAuthorDto(firstName, lastName);

        assertNotNull(result);
        assertEquals(firstName, result.getFirstName());
        assertEquals(lastName, result.getLastName());
    }

    @Test
    public void testGetAuthorDtoById() {
        Long authorId = 1L;
        String firstName = "John";
        String lastName = "Doe";

        Author author = new Author();
        author.setIdAuthor(authorId);
        author.setFirstName(firstName);
        author.setLastName(lastName);

        when(authorRepository.findById(authorId)).thenReturn(Optional.of(author));

        AuthorDto result = authorService.getAuthorDtoById(authorId);

        assertNotNull(result);
        assertEquals(firstName, result.getFirstName());
        assertEquals(lastName, result.getLastName());
    }

    @Test
    public void testUpdateAuthorDto() {
        Long authorId = 1L;
        String newFirstName = "Jane";
        String newLastName = "Smith";

        Author author = new Author();
        author.setIdAuthor(authorId);

        when(authorRepository.findById(authorId)).thenReturn(Optional.of(author));
        when(authorRepository.save(any(Author.class))).thenReturn(author);

        AuthorDto result = authorService.updateAuthorDto(authorId, newFirstName, newLastName);

        assertNotNull(result);
        assertEquals(newFirstName, result.getFirstName());
        assertEquals(newLastName, result.getLastName());
    }

    @Test
    public void testDeleteAuthor() {
        Long authorId = 1L;

        authorService.deleteAuthor(authorId);

        verify(authorRepository).deleteById(authorId);
    }

    @Test
    public void testGetAllAuthorsDto() {
        Author author1 = new Author();
        author1.setIdAuthor(1L);
        author1.setFirstName("John");
        author1.setLastName("Doe");

        Author author2 = new Author();
        author2.setIdAuthor(2L);
        author2.setFirstName("Jane");
        author2.setLastName("Smith");

        when(authorRepository.findAll()).thenReturn(Arrays.asList(author1, author2));

        List<AuthorDto> result = authorService.getAllAuthorsDto();

        assertEquals(2, result.size());
        assertEquals("John", result.get(0).getFirstName());
        assertEquals("Jane", result.get(1).getFirstName());
    }
}
