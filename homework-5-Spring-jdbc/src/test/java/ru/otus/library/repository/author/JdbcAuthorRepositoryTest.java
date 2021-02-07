package ru.otus.library.repository.author;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.library.domain.entity.Author;
import ru.otus.library.domain.entity.AuthorInfo;
import ru.otus.library.domain.entity.Book;
import ru.otus.library.repository.book.BookRepository;
import ru.otus.library.repository.book.JdbcBookRepository;

import java.util.ArrayList;
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

    @DisplayName("должен вставить автора и вернуть его обратно")
    @Test
    void shouldCreateAndReturnAuthor() {
        Author exceptedAuthor = new Author();
        exceptedAuthor.setName("Глуховский, Дмитрий Алексеевич");

        Author author = new Author();
        author.setName("Глуховский, Дмитрий Алексеевич");
        AuthorInfo authorInfo = new AuthorInfo(author, new ArrayList<>());
        Author actualAuthor = authorRepository.createAuthor(authorInfo);

        Assertions.assertThat(actualAuthor.getName()).isEqualTo(exceptedAuthor.getName());
    }

    @DisplayName("должен обновлять автора")
    @Test
    void shouldUpdateAuthor() {
        Author expectedAuthor = new Author(1L, "Eric");
        List<Book> books = Collections.singletonList(Book.builder().id(1L).build());

        AuthorInfo authorInfo = new AuthorInfo(new Author(1L, "Eric"), books);
        authorRepository.updateAuthor(authorInfo);
        Author actualAuthor = authorRepository.getById(1L);
        Assertions.assertThat(actualAuthor).isEqualTo(expectedAuthor);

        List<Book> actualBook = bookRepository.getByAuthorId(actualAuthor.getId());

        Book expectedBook = Book.builder()
                .id(1L)
                .title("Python Crash Course, 2nd Edition: A Hands-On, Project-Based Introduction to Programming")
                .build();

        List<Book> expectedBooks = Collections.singletonList(expectedBook);
        Assertions.assertThat(actualBook).isEqualTo(expectedBooks);
    }

    @DisplayName("должен удалять автора")
    @Test
    void shouldDeleteAuthor() {
        List<Author> authors = authorRepository.getAll();
        int beforeCount = authors.size();
        authorRepository.deleteAuthor(1L);
        List<Author> afterDeleteAuthors = authorRepository.getAll();
        Assertions.assertThat(afterDeleteAuthors.size()).isEqualTo(beforeCount - 1);
    }
}