package ru.otus.library.repository.author;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.library.domain.entity.Author;
import ru.otus.library.domain.entity.Book;
import ru.otus.library.domain.entity.Genre;
import ru.otus.library.repository.book.BookRepository;
import ru.otus.library.repository.book.JdbcBookRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@DisplayName("Тест для работы с авторами")
@JdbcTest
@ExtendWith(SpringExtension.class)
@Import({JdbcAuthorRepository.class, JdbcBookRepository.class})
class JdbcAuthorRepositoryTest {
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @DisplayName("должен вставить автора и вернуть его обратно")
    @Test
    void shouldCreateAndReturnAuthor() {
        Author exceptedAuthor = new Author();
        exceptedAuthor.setName("Глуховский, Дмитрий Алексеевич");

        Author author = new Author();
        author.setName("Глуховский, Дмитрий Алексеевич");
        Author actualAuthor = authorRepository.createAuthor(author);
        Assertions.assertThat(actualAuthor.getName()).isEqualTo(exceptedAuthor.getName());
    }

    @DisplayName("должен обновлять автора")
    @Test
    void shouldUpdateAuthor() {
        Author expectedAuthor = new Author(1L, "Eric");

        authorRepository.updateAuthor(new Author(1L, "Eric"), Arrays.asList(1L));
        Author actualAuthor = authorRepository.getById(1L);
        Assertions.assertThat(actualAuthor).isEqualTo(expectedAuthor);

        List<Book> actualBook = bookRepository.getByAuthorId(actualAuthor.getId());
        Book expectedBook = Book.builder()
                .id(1L)
                .title("Python Crash Course, 2nd Edition: A Hands-On, Project-Based Introduction to Programming")
                .authors(Collections.singletonList(actualAuthor))
                .genres(Collections.singletonList(new Genre(1L, "Programming")))
                .build();
        List<Book> expectedBooks = Collections.singletonList(expectedBook);
        Assertions.assertThat(actualBook).isEqualTo(expectedBooks);
    }
}