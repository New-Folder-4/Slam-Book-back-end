package com.system.slam.controller;

import com.system.slam.web.dto.BookDto;
import com.system.slam.entity.BookLiterary;
import com.system.slam.service.BookLiteraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookLiteraryService bookService;

    @Autowired
    public BookController(BookLiteraryService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public BookDto createBook(@RequestBody BookDto dto) {
        BookLiterary saved = bookService.createBookLiterary(
                dto.getIdAuthor(),
                dto.getBookName(),
                dto.getNote()
        );
        return convertToDto(saved);
    }

    @GetMapping
    public List<BookDto> getAllBooks() {
        return bookService
                .findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public BookDto getBookById(@PathVariable Long id) {
        BookLiterary book = bookService.getBookLiterary(id);
        return convertToDto(book);
    }

    @PutMapping("/{id}")
    public BookDto updateBook(@PathVariable Long id, @RequestBody BookDto dto) {
        BookLiterary updated = bookService.updateBookLiterary(
                id,
                dto.getBookName(),
                dto.getNote()
        );
        return convertToDto(updated);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBookLiterary(id);
    }

    private BookDto convertToDto(BookLiterary book) {
        return new BookDto(
                book.getIdBookLiterary(),
                (book.getAuthor() != null) ? book.getAuthor().getIdAuthor() : null,
                book.getBookName(),
                book.getNote()
        );
    }
}
