package com.system.slam.service;

import com.system.slam.entity.Autor;
import com.system.slam.entity.BookLiterary;
import com.system.slam.repository.BookLiteraryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookLiteraryServiceTest {

    @Mock
    private BookLiteraryRepository bookLiteraryRepository;

    @Mock
    private AutorService autorService;

    @InjectMocks
    private BookLiteraryService bookLiteraryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateBookLiterary() {
        Long autorId = 1L;
        String bookName = "Test Book";
        String note = "Test Note";

        Autor autor = new Autor();
        BookLiterary book = new BookLiterary();

        when(autorService.getAuthor(autorId)).thenReturn(autor);
        when(bookLiteraryRepository.save(any(BookLiterary.class))).thenReturn(book);

        BookLiterary result = bookLiteraryService.createBookLiterary(autorId, bookName, note);

        assertNotNull(result);
        assertEquals(bookName, result.getBookName());
        assertEquals(note, result.getNote());
    }

    @Test
    void testGetBookLiterary() {
        Long bookId = 1L;
        BookLiterary book = new BookLiterary();
        book.setBookName("Test Book");
        book.setNote("Test Note");

        when(bookLiteraryRepository.findById(bookId)).thenReturn(Optional.of(book));

        BookLiterary result = bookLiteraryService.getBookLiterary(bookId);

        assertNotNull(result);
        assertEquals("Test Book", result.getBookName());
        assertEquals("Test Note", result.getNote());
    }

    @Test
    void testGetBookLiterary_BookNotFound() {
        Long bookId = 1L;

        when(bookLiteraryRepository.findById(bookId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            bookLiteraryService.getBookLiterary(bookId);
        });

        assertEquals("BookLiterary not found, id=" + bookId, exception.getMessage());
    }

    @Test
    void testUpdateBookLiterary() {
        Long bookId = 1L;
        String newBookName = "Updated Book";
        String newNote = "Updated Note";

        BookLiterary book = new BookLiterary();

        when(bookLiteraryRepository.findById(bookId)).thenReturn(Optional.of(book));
        when(bookLiteraryRepository.save(any(BookLiterary.class))).thenReturn(book);

        BookLiterary result = bookLiteraryService.updateBookLiterary(bookId, newBookName, newNote);

        assertNotNull(result);
        assertEquals(newBookName, result.getBookName());
        assertEquals(newNote, result.getNote());
    }

    @Test
    void testDeleteBookLiterary() {
        Long bookId = 1L;

        bookLiteraryService.deleteBookLiterary(bookId);

        verify(bookLiteraryRepository, times(1)).deleteById(bookId);
    }

    @Test
    void testFindByBookName() {
        String namePart = "Test";
        BookLiterary book1 = new BookLiterary();
        book1.setBookName("Test Book 1");
        BookLiterary book2 = new BookLiterary();
        book2.setBookName("Another Test Book");

        when(bookLiteraryRepository.findAll()).thenReturn(List.of(book1, book2));

        List<BookLiterary> result = bookLiteraryService.findByBookName(namePart);

        assertEquals(2, result.size());
    }
}
