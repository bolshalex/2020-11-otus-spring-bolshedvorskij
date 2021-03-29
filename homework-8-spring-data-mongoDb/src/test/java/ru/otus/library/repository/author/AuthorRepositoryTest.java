package ru.otus.library.repository.author;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import ru.otus.library.domain.entity.Author;
import ru.otus.library.repository.book.BookRepository;

import java.util.List;
import java.util.Optional;

@DisplayName("Тест для работы с авторами")
@DataMongoTest
@EnableMongock
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;

    @DisplayName("должен вставить автора и вернуть его обратно")
    @Test
    void shouldCreateAndReturnAuthor() {
        Author exceptedAuthor = Author.builder()
                .name("Глуховский, Дмитрий Алексеевич")
                .build();

        Author author = Author.builder()
                .name("Глуховский, Дмитрий Алексеевич")
                .build();

        Author actualAuthor = authorRepository.save(author);
        Assertions.assertThat(actualAuthor.getName()).isEqualTo(exceptedAuthor.getName());
    }

    @DisplayName("должен обновлять автора")
    @Test
    void shouldUpdateAuthor() {
        Author expectedAuthor = new Author("1", "Eric");
        authorRepository.save(expectedAuthor);
        Optional<Author> actualAuthor = authorRepository.findById("1");
        Assertions.assertThat(actualAuthor.get()).isEqualTo(expectedAuthor);
    }

    @DisplayName("должен удалять автора")
    @Test
    void shouldDeleteAuthor() {
        List<Author> authors = authorRepository.findAll();
        int beforeCount = authors.size();
        authorRepository.deleteById("1");
        List<Author> afterDeleteAuthors = authorRepository.findAll();
        Assertions.assertThat(afterDeleteAuthors.size()).isEqualTo(beforeCount - 1);
    }
}