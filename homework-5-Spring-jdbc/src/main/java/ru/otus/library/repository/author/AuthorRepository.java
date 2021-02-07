package ru.otus.library.repository.author;

import ru.otus.library.domain.entity.Author;
import ru.otus.library.domain.entity.AuthorInfo;

import java.util.List;

public interface AuthorRepository {

    Author createAuthor(AuthorInfo authorInfo);

    void updateAuthor(AuthorInfo authorInfo);

    void deleteAuthor(Long id);

    Author getById(Long id);

    Author getByName(String name);

    List<Author> getByBookId(Long bookId);

    List<Author> getAll();
}
