package ru.otus.library.service.genre;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.library.domain.dto.GenreDto;
import ru.otus.library.domain.entity.Genre;
import ru.otus.library.repository.genre.GenreRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public GenreDto createGenre(GenreDto genreDto) {
        Genre genre = Genre.builder().
                name(genreDto.getName())
                .build();
        Genre createdGenre = genreRepository.save(genre);
        return GenreDto.builder()
                .id(createdGenre.getId())
                .name(createdGenre.getName())
                .build();
    }

    @Override
    public void updateGenre(GenreDto genreDto) {
        Genre genre = new Genre(genreDto.getId(), genreDto.getName());
        genreRepository.save(genre);
    }

    @Override
    public void deleteGenre(String genreId) {
        genreRepository.deleteById(genreId);
    }

    @Override
    public GenreDto getGenre(String genreId) {

        Optional<Genre> genreOptional = genreRepository.findById(genreId);

        if (genreOptional.isEmpty()) {
            return new GenreDto();
        }
        Genre genre = genreOptional.get();
        return GenreDto.builder()
                .id(genre.getId())
                .name(genre.getName())
                .build();
    }

    @Override
    public List<GenreDto> getAllGenres() {
        List<Genre> genres = genreRepository.findAll();
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
