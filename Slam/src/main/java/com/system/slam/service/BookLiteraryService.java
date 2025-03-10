package com.system.slam.service;

import com.system.slam.entity.Author;
import com.system.slam.entity.BookLiterary;
import com.system.slam.repository.BookLiteraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookLiteraryService {

    private final BookLiteraryRepository bookLiteraryRepository;
    private final AuthorService authorService;

    @Autowired
    public BookLiteraryService(BookLiteraryRepository bookLiteraryRepository,
                               AuthorService authorService) {
        this.bookLiteraryRepository = bookLiteraryRepository;
        this.authorService = authorService;
    }

    public BookLiterary createBookLiterary(Long authorId, String bookName, String note) {

        Author author = authorService.getAuthor(authorId);

        BookLiterary book = new BookLiterary();
        book.setAutor(author);
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

