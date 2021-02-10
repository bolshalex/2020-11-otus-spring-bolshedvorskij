package ru.otus.library.repository.author;

import ru.otus.library.domain.entity.Author;
import ru.otus.library.domain.entity.Book;

import java.util.List;
import java.util.Map;

public interface AuthorRepository {

    Author createAuthor(Author author);

    void updateAuthor(Author author);

    void addAuthorBooks(Author author, List<Book> books);

    void updateAuthorBooks(Author author, List<Book> books);

    void deleteAuthor(Long id);

    Author getById(Long id);

    Author getByName(String name);

    List<Author> getByBookId(Long bookId);

    Map<Long, List<Author>> getAuthorsByBookIds(List<Long> bookIds);

    List<Author> getAll();
}
