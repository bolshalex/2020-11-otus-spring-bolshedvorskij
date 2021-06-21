package ru.otus.library.service.book;

import ru.otus.library.domain.dto.BookDto;

import java.util.List;

public interface BookService {
    BookDto createBook(BookDto book);

    void updateBook(BookDto book);

    void deleteBook(String id);

    BookDto getById(String id);

    List<BookDto> getByAuthorId(String authorId);

    List<BookDto> getAll();
}
