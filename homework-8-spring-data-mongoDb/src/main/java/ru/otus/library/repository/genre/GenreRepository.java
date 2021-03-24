package ru.otus.library.repository.genre;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.library.domain.entity.Genre;

public interface GenreRepository extends MongoRepository<Genre, String>, CustomGenreRepository<String> {

}
