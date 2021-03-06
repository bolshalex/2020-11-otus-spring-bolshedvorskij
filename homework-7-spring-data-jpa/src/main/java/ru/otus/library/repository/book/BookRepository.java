package ru.otus.library.repository.book;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.library.domain.entity.Author;
import ru.otus.library.domain.entity.Book;
import ru.otus.library.domain.entity.Genre;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    Book getById(Long id);

    List<Book> getByAuthorsId(Long authorId);

    void deleteById(Long id);
}
