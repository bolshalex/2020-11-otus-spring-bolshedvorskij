package ru.otus.library.repository.author;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.library.domain.entity.Author;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {


    Author getById(Long id);

    Author getFirstByName(String name);

    List<Author> getAllByBooksId(Long bookId);

    void deleteById(Long id);
}
