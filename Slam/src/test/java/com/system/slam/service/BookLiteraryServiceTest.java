package com.system.slam.service;

import com.system.slam.entity.Author;
import com.system.slam.entity.BookLiterary;
import com.system.slam.repository.BookLiteraryRepository;
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

public class BookLiteraryServiceTest {

    @Mock
    private BookLiteraryRepository bookLiteraryRepository;

    @Mock
    private AuthorService authorService;

    @InjectMocks
    private BookLiteraryService bookLiteraryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateBookLiterary() {
        Long authorId = 1L;
        String bookName = "Sample Book";
        String note = "Sample Note";

        Author author = new Author();
        author.setIdAuthor(authorId);

        BookLiterary book = new BookLiterary();
        book.setIdBookLiterary(1L);
        book.setAutor(author);
        book.setBookName(bookName);
        book.setNote(note);

        when(authorService.getAuthor(authorId)).thenReturn(author);
        when(bookLiteraryRepository.save(any(BookLiterary.class))).thenReturn(book);

        BookLiterary result = bookLiteraryService.createBookLiterary(authorId, bookName, note);

        assertNotNull(result);
        assertEquals(bookName, result.getBookName());
        assertEquals(note, result.getNote());
        assertEquals(authorId, result.getAutor().getIdAuthor());
    }

    @Test
    public void testGetBookLiterary() {
        Long bookId = 1L;
        String bookName = "Sample Book";
        String note = "Sample Note";

        BookLiterary book = new BookLiterary();
        book.setIdBookLiterary(bookId);
        book.setBookName(bookName);
        book.setNote(note);

        when(bookLiteraryRepository.findById(bookId)).thenReturn(Optional.of(book));

        BookLiterary result = bookLiteraryService.getBookLiterary(bookId);

        assertNotNull(result);
        assertEquals(bookName, result.getBookName());
        assertEquals(note, result.getNote());
    }

    @Test
    public void testUpdateBookLiterary() {
        Long bookId = 1L;
        String newBookName = "Updated Book";
        String newNote = "Updated Note";

        BookLiterary book = new BookLiterary();
        book.setIdBookLiterary(bookId);

        when(bookLiteraryRepository.findById(bookId)).thenReturn(Optional.of(book));
        when(bookLiteraryRepository.save(any(BookLiterary.class))).thenReturn(book);

        BookLiterary result = bookLiteraryService.updateBookLiterary(bookId, newBookName, newNote);

        assertNotNull(result);
        assertEquals(newBookName, result.getBookName());
        assertEquals(newNote, result.getNote());
    }

    @Test
    public void testDeleteBookLiterary() {
        Long bookId = 1L;

        bookLiteraryService.deleteBookLiterary(bookId);

        verify(bookLiteraryRepository).deleteById(bookId);
    }

    @Test
    public void testFindByBookName() {
        String namePart = "Sample";

        BookLiterary book1 = new BookLiterary();
        book1.setBookName("Sample Book 1");

        BookLiterary book2 = new BookLiterary();
        book2.setBookName("Another Sample Book");

        when(bookLiteraryRepository.findAll()).thenReturn(Arrays.asList(book1, book2));

        List<BookLiterary> result = bookLiteraryService.findByBookName(namePart);

        assertEquals(2, result.size());
        assertTrue(result.stream().allMatch(b -> b.getBookName().contains(namePart)));
    }
}
