package com.system.slam.controller;

import com.system.slam.web.dto.AuthorDto;
import com.system.slam.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public AuthorDto createAuthor(@RequestBody AuthorDto authorDto) {
        return authorService.createAuthorDto(authorDto.getFirstName(), authorDto.getLastName());
    }

    @GetMapping
    public List<AuthorDto> getAllAuthors() {
        return authorService.getAllAuthorsDto();
    }

    @GetMapping("/{id}")
    public AuthorDto getAuthorById(@PathVariable Long id) {
        return authorService.getAuthorDtoById(id);
    }

    @PutMapping("/{id}")
    public AuthorDto updateAuthor(@PathVariable Long id, @RequestBody AuthorDto authorDto) {
        return authorService.updateAuthorDto(id, authorDto.getFirstName(), authorDto.getLastName());
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
    }
}

