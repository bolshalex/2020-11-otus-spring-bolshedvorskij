package ru.otus.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.otus.library.domain.dto.AuthorDto;
import ru.otus.library.service.author.AuthorService;

import java.util.List;

@RestController
public class AuthorController {
    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/api/authors")
    public List<AuthorDto> getAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping(value = "/api/authors", params = {"name"})
    public List<AuthorDto> getAuthors(@RequestParam("name") String name) {
        return authorService.getAuthorsByName(name);
    }

    @GetMapping("/api/authors/{id}")
    public AuthorDto getAuthor(@PathVariable("id") String authorId) {
        return this.authorService.getAuthor(authorId);
    }

    @PostMapping("/api/authors")
    public void createAuthor(@RequestBody AuthorDto author) {
        authorService.createAuthor(author);
    }

    @PutMapping("/api/authors")
    public void updateAuthor(@RequestBody AuthorDto author) {
        authorService.updateAuthor(author);
    }

    @DeleteMapping("/api/authors/{id}")
    public void deleteAuthor(@PathVariable("id") String authorId) {
        authorService.deleteAuthor(authorId);
    }
}
