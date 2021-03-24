package ru.otus.library.service.book;

import ru.otus.library.domain.dto.BookDto;

import java.util.List;

public interface BookService {
    BookDto createBook(String bookTitle, List<String> authorIds, List<String> genreIds);

    void updateBook(String bookId, String title, List<String> authorIds, List<String> genreIds);

    void deleteBook(String id);

    BookDto getById(String id);

    List<BookDto> getByAuthorId(String authorId);

    List<BookDto> getAll();
}
