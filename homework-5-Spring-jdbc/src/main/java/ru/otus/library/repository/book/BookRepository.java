package ru.otus.library.repository.book;

import ru.otus.library.domain.entity.Author;
import ru.otus.library.domain.entity.Book;
import ru.otus.library.domain.entity.BookInfo;
import ru.otus.library.domain.entity.Genre;

import java.util.List;

public interface BookRepository {
    Book createBook(BookInfo bookInfo);

    void addBookAuthors(Book book, List<Author> authors);

    void addBookGenres(Book book, List<Genre> genres);

    void updateBook(BookInfo bookInfo);

    void deleteBook(Long id);

    Book getById(Long id);

    List<Book> getByAuthorId(Long authorId);

    List<Book> getAll();
}
