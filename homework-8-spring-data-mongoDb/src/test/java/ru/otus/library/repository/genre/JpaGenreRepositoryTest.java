package ru.otus.library.repository.genre;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.otus.library.domain.entity.Genre;

import java.util.ArrayList;
import java.util.List;

@DisplayName("Тест для работы с жанрами")
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class JpaGenreRepositoryTest {

    @Autowired
    private GenreRepository genreRepository;

    @DisplayName("должен создавать жанр")
    @Test
    void shouldCreateGenre() {
        Genre genre = Genre.builder().name("Science fiction").build();
        Genre createdGenre = genreRepository.saveAndFlush(genre);
        Assertions.assertThat(genre.getName()).isEqualTo(createdGenre.getName());
    }

    @DisplayName("должен обновлять жанр")
    @Test
    void shouldUpdateGenre() {
        Genre genre = new Genre(2L, "Science");
        genreRepository.saveAndFlush(genre);
        Genre updatedGenre = genreRepository.getById(2L);
        Assertions.assertThat(genre).isEqualTo(updatedGenre);

    }

    @DisplayName("должен удалять жанр")
    @Test
    void shouldDeleteGenre() {
        List<Genre> beforeGenres = genreRepository.findAll();
        genreRepository.deleteById(1L);
        List<Genre> afterGenres = genreRepository.findAll();
        Assertions.assertThat(beforeGenres.size() - 1).isEqualTo(afterGenres.size());
    }

    @DisplayName("должен находить жанр по id")
    @Test
    void shouldGetGenreById() {
        Genre expectedGenre = new Genre(1L, "Programming");

        Genre actualGenre = genreRepository.getById(expectedGenre.getId());
        Assertions.assertThat(expectedGenre).isEqualTo(actualGenre);
    }

    @DisplayName("должен получать все жанры")
    @Test
    void getAll() {
        List<Genre> expectedGenres = new ArrayList<>();
        expectedGenres.add(new Genre(1L, "Programming"));
        expectedGenres.add(new Genre(2L, "Computer Science"));

        List<Genre> actualGenres = genreRepository.findAll();
        Assertions.assertThat(expectedGenres).containsExactlyInAnyOrderElementsOf(actualGenres);
    }

}