package ru.otus.library.repository.genre;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import ru.otus.library.domain.entity.Genre;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DisplayName("Тест для работы с жанрами")
@DataMongoTest
@EnableMongock
class JpaGenreRepositoryTest {

    @Autowired
    private GenreRepository genreRepository;

    @DisplayName("должен создавать жанр")
    @Test
    void shouldCreateGenre() {
        Genre genre = Genre.builder().name("Science fiction").build();
        Genre createdGenre = genreRepository.save(genre);
        Assertions.assertThat(genre.getName()).isEqualTo(createdGenre.getName());
    }

    @DisplayName("должен обновлять жанр")
    @Test
    void shouldUpdateGenre() {
        Genre genre = new Genre("2", "Science");
        genreRepository.save(genre);
        Optional<Genre> updatedGenre = genreRepository.findById("2");
        Assertions.assertThat(genre).isEqualTo(updatedGenre.get());

    }

    @DisplayName("должен удалять жанр")
    @Test
    void shouldDeleteGenre() {
        List<Genre> beforeGenres = genreRepository.findAll();
        genreRepository.deleteById("1");
        List<Genre> afterGenres = genreRepository.findAll();
        Assertions.assertThat(beforeGenres.size() - 1).isEqualTo(afterGenres.size());
    }

    @DisplayName("должен находить жанр по id")
    @Test
    void shouldGetGenreById() {
        Genre expectedGenre = new Genre("1", "Programming");

        Optional<Genre> actualGenre = genreRepository.findById(expectedGenre.getId());
        Assertions.assertThat(expectedGenre).isEqualTo(actualGenre.get());
    }

    @DisplayName("должен получать все жанры")
    @Test
    void getAll() {
        List<Genre> expectedGenres = new ArrayList<>();
        expectedGenres.add(new Genre("1", "Programming"));
        expectedGenres.add(new Genre("2", "Computer Science"));

        List<Genre> actualGenres = genreRepository.findAll();
        Assertions.assertThat(expectedGenres).containsExactlyInAnyOrderElementsOf(actualGenres);
    }

}