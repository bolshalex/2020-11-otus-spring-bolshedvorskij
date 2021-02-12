package ru.otus.library.service.book;

import ru.otus.library.domain.dto.BookDto;

import java.util.List;

public interface BookService {
    BookDto createBook(String bookTitle, List<Long> authorIds, List<Long> genreIds);

    void updateBook(Long bookId, String title, List<Long> authorIds, List<Long> genreIds);

    void deleteBook(Long id);

    BookDto getById(Long id);

    List<BookDto> getByAuthorId(Long authorId);

    List<BookDto> getAll();
}
