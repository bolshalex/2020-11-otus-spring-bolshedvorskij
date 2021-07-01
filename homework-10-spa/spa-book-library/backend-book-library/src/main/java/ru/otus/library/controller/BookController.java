package ru.otus.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.otus.library.domain.dto.BookDto;
import ru.otus.library.service.book.BookService;

import java.util.List;

@RestController
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/api/books")
    public List<BookDto> getBooks() {
        return bookService.getAll();
    }

    @GetMapping("/api/books/{id}")
    public BookDto getBook(@PathVariable("id") String id) {
        return bookService.getById(id);
    }

    @PostMapping("/api/books")
    public void createBook(@RequestBody BookDto book) {
        bookService.createBook(book);
    }

    @PutMapping("/api/books/{id}")
    public void updateBook(@PathVariable("id") String id, @RequestBody BookDto book) {
        book.setBookId(id);
        bookService.updateBook(book);
    }

    @DeleteMapping("/api/books/{id}")
    public void deleteBook(@PathVariable("id") String id) {
        bookService.deleteBook(id);
    }

}
