package ru.otus.library.service.genre;

import ru.otus.library.domain.dto.GenreDto;

import java.util.List;

public interface GenreService {
    GenreDto createGenre(String name);

    void updateGenre(Long genreId, String name);

    void deleteGenre(Long genreId);

    GenreDto getGenre(Long genreId);

    List<GenreDto> getAllGenres();
}
