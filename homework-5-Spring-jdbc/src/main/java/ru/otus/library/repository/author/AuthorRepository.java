package ru.otus.library.repository.author;

import ru.otus.library.domain.entity.Author;

import java.util.List;

public interface AuthorRepository {

    Author createAuthor(String name);

    void updateAuthor(Long id, String name, List<Long> bookIds);

    void deleteAuthor(Long id);

    void getById(Long id);

    List<Author> getAll();
}
