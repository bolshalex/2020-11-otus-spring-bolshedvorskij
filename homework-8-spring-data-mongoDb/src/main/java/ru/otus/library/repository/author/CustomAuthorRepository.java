package ru.otus.library.repository.author;

public interface CustomAuthorRepository<ID> {
    void deleteById(ID id);
}
