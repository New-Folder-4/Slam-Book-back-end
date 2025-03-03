package com.system.slam.service;

import com.system.slam.entity.Autor;
import com.system.slam.entity.BookLiterary;
import com.system.slam.repository.BookLiteraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookLiteraryService {

    private final BookLiteraryRepository bookLiteraryRepository;
    private final AutorService autorService;

    @Autowired
    public BookLiteraryService(BookLiteraryRepository bookLiteraryRepository,
                               AutorService autorService) {
        this.bookLiteraryRepository = bookLiteraryRepository;
        this.autorService = autorService;
    }

    public BookLiterary createBookLiterary(Long autorId, String bookName, String note) {

        Autor autor = autorService.getAuthor(autorId);

        BookLiterary book = new BookLiterary();
        book.setAutor(autor);
        book.setBookName(bookName);
        book.setNote(note);

        return bookLiteraryRepository.save(book);
    }

    public BookLiterary getBookLiterary(Long bookId) {
        return bookLiteraryRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("BookLiterary not found, id=" + bookId));
    }

    public BookLiterary updateBookLiterary(Long bookId, String newBookName, String newNote) {
        BookLiterary book = getBookLiterary(bookId);
        book.setBookName(newBookName);
        book.setNote(newNote);
        return bookLiteraryRepository.save(book);
    }

    public void deleteBookLiterary(Long bookId) {
        bookLiteraryRepository.deleteById(bookId);
    }

    public List<BookLiterary> findByBookName(String namePart) {
        return bookLiteraryRepository.findAll().stream()
                .filter(b -> b.getBookName() != null && b.getBookName().contains(namePart))
                .collect(Collectors.toList());
    }
}

