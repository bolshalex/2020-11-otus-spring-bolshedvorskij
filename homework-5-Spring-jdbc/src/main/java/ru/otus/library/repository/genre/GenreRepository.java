package ru.otus.library.repository.genre;

import ru.otus.library.domain.entity.Genre;

import java.util.List;

public interface GenreRepository {
    Genre createGenre(Genre genre);

    void updateGenre(Genre genre);

    void deleteGenre(Long id);

    Genre getById(Long id);

    List<Genre> getByBookId(Long bookId);

    List<Genre> getAll();

}
