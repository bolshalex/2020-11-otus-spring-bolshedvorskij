package ru.otus.library.service;

import ru.otus.library.domain.dto.BookDto;

import java.util.List;

public interface BookService {
    BookDto createBook(String bookTitle, List<Long> authorIds, List<Long> genreIds);

    void addBookAuthors(Long bookId, List<Long> authorIds);

    void addBookGenres(Long bookId, List<Long> genreIds);

    void updateBook(Long bookId, String title, List<Long> authorIds, List<Long> genreIds);

    void deleteBook(Long id);

    BookDto getById(Long id);

    List<BookDto> getByAuthorId(Long authorId);

    List<BookDto> getAll();
}
