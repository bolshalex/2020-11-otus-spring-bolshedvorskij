package ru.otus.library.repository.book;

import ru.otus.library.domain.entity.Author;

import java.util.List;

public interface CustomBookRepository<T, ID> {

    void putAuthorToBooks(Author author, List<T> t);
}
