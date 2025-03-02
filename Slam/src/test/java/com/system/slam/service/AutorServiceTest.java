package com.system.slam.service;

import com.system.slam.entity.Autor;
import com.system.slam.repository.AutorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AutorServiceTest {

    @Mock
    private AutorRepository autorRepository;

    @InjectMocks
    private AutorService autorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateAuthor() {
        String firstName = "John";
        String lastName = "Doe";
        Autor autor = new Autor();

        when(autorRepository.save(any(Autor.class))).thenReturn(autor);

        Autor result = autorService.createAuthor(firstName, lastName);

        assertNotNull(result);
        assertEquals(firstName, result.getFirstName());
        assertEquals(lastName, result.getLastName());
    }

    @Test
    void testGetAuthor() {
        Long autorId = 1L;
        Autor autor = new Autor();
        autor.setFirstName("John");
        autor.setLastName("Doe");

        when(autorRepository.findById(autorId)).thenReturn(Optional.of(autor));

        Autor result = autorService.getAuthor(autorId);

        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());
    }

    @Test
    void testGetAuthor_AuthorNotFound() {
        Long autorId = 1L;

        when(autorRepository.findById(autorId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            autorService.getAuthor(autorId);
        });

        assertEquals("Author not found, id=" + autorId, exception.getMessage());
    }

    @Test
    void testUpdateAuthor() {
        Long autorId = 1L;
        String newFirstName = "Jane";
        String newLastName = "Smith";
        Autor autor = new Autor();

        when(autorRepository.findById(autorId)).thenReturn(Optional.of(autor));
        when(autorRepository.save(any(Autor.class))).thenReturn(autor);

        Autor result = autorService.updateAuthor(autorId, newFirstName, newLastName);

        assertNotNull(result);
        assertEquals(newFirstName, result.getFirstName());
        assertEquals(newLastName, result.getLastName());
    }

    @Test
    void testDeleteAuthor() {
        Long autorId = 1L;

        autorService.deleteAuthor(autorId);

        verify(autorRepository, times(1)).deleteById(autorId);
    }

    @Test
    void testFindByFirstNameAndLastName() {
        String firstName = "John";
        String lastName = "Doe";
        Autor autor = new Autor();

        when(autorRepository.findByFirstNameAndLastName(firstName, lastName)).thenReturn(Optional.of(autor));

        Optional<Autor> result = autorService.findByFirstNameAndLastName(firstName, lastName);

        assertTrue(result.isPresent());
        assertEquals(firstName, result.get().getFirstName());
        assertEquals(lastName, result.get().getLastName());
    }
}
