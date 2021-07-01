package ru.otus.library.repository.author;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.library.domain.entity.Author;

import java.util.List;

public interface AuthorRepository extends MongoRepository<Author, String>, CustomAuthorRepository<String> {
    Author getByName(String name);

    List<Author> getAllByNameLike(String name);
}
