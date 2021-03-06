package ru.otus.library.service.genre;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.domain.dto.GenreDto;
import ru.otus.library.domain.entity.Genre;
import ru.otus.library.repository.genre.GenreRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Transactional
    @Override
    public GenreDto createGenre(String name) {
        Genre genre = Genre.builder().name(name).build();
        Genre createdGenre = genreRepository.createGenre(genre);
        return GenreDto.builder()
                .id(createdGenre.getId())
                .name(createdGenre.getName())
                .build();
    }

    @Transactional
    @Override
    public void updateGenre(Long genreId, String name) {
        Genre genre = new Genre(genreId, name);
        genreRepository.updateGenre(genre);
    }

    @Transactional
    @Override
    public void deleteGenre(Long genreId) {
        genreRepository.deleteGenre(genreId);
    }

    @Transactional(readOnly = true)
    @Override
    public GenreDto getGenre(Long genreId) {

        Genre genre = genreRepository.getById(genreId);

        return GenreDto.builder()
                .id(genre.getId())
                .name(genre.getName())
                .build();
    }

    @Transactional(readOnly = true)
    @Override
    public List<GenreDto> getAllGenres() {
        List<Genre> genres = genreRepository.getAll();
        List<GenreDto> genreDtoList = new ArrayList<>();
        if (CollectionUtils.isEmpty(genres)) {
            return genreDtoList;
        }

        for (Genre genre : genres) {
            GenreDto genreDto = GenreDto.builder()
                    .id(genre.getId())
                    .name(genre.getName())
                    .build();
            genreDtoList.add(genreDto);
        }
        return genreDtoList;
    }
}
