package ru.otus.library.service.genre;

import ru.otus.library.domain.dto.GenreDto;

import java.util.List;

public interface GenreService {
    GenreDto createGenre(GenreDto genre);

    void updateGenre(GenreDto genreDto);

    void deleteGenre(String genreId);

    GenreDto getGenre(String genreId);

    List<GenreDto> getAllGenres();
}
