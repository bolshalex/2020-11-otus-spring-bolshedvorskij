package ru.otus.library.repository.genre;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.library.domain.entity.Genre;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    Genre getById(Long id);

    void deleteById(Long id);
}
