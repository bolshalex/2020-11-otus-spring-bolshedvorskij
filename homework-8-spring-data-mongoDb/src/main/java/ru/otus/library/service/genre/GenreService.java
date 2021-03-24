package ru.otus.library.service.genre;

import ru.otus.library.domain.dto.GenreDto;

import java.util.List;

public interface GenreService {
    GenreDto createGenre(String name);

    void updateGenre(String genreId, String name);

    void deleteGenre(String genreId);

    GenreDto getGenre(String genreId);

    List<GenreDto> getAllGenres();
}
