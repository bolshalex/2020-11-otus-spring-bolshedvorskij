package ru.otus.library.repository.book;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.library.domain.entity.Book;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {

    List<Book> getByAuthorsId(String authorId);

}
