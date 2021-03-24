package ru.otus.library.repository.genre;

public interface CustomGenreRepository<ID> {
    void deleteById(ID id);
}
