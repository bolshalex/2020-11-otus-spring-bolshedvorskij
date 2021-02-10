package ru.otus.library.repository.genre;

import ru.otus.library.domain.entity.Book;
import ru.otus.library.domain.entity.Genre;

import java.util.List;
import java.util.Map;

public interface GenreRepository {
    Genre createGenre(Genre genre);

    void updateGenre(Genre genre);

    void deleteGenre(Long id);

    Genre getById(Long id);

    List<Genre> getByBookId(Long bookId);

    Map<Long, List<Genre>> getBookGenres(List<Long> bookIds);

    List<Genre> getAll();

}
