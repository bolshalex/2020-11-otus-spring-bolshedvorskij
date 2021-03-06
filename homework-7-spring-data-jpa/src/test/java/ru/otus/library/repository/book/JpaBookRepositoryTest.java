package ru.otus.library.repository.book;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.otus.library.domain.entity.Author;
import ru.otus.library.domain.entity.Book;
import ru.otus.library.domain.entity.Genre;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@DisplayName("Тест для работы с книгами")
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class JpaBookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @DisplayName("должен создавать книгу")
    @Test
    void createBook() {
        Book book = Book.builder().title("On Lisp: Advanced Techniques for Common Lisp").build();
        Book createdBook = bookRepository.saveAndFlush(book);
        Assertions.assertThat(book.getTitle()).isEqualTo(createdBook.getTitle());
    }

    @DisplayName("должен обновлять книгу")
    @Test
    void shouldUpdateBook() {

        List<Author> authors = Collections.singletonList(new Author(3L, "Erich Gamma"));
        List<Genre> genres = Collections.singletonList(new Genre(2L, "Computer Science"));

        Book book = Book.builder()
                .id(4L)
                .title("Design Patterns: Elements of Reusable Object-Oriented Software 1st Edition")
                .authors(authors)
                .genres(genres)
                .build();

        bookRepository.saveAndFlush(book);
        Book updatedBook = bookRepository.getById(book.getId());

        Assertions.assertThat(book).isEqualTo(updatedBook);
    }

    @DisplayName("должен удалять книгу")
    @Test
    void shouldDeleteBook() {
        List<Book> beforeBooks = bookRepository.findAll();
        bookRepository.deleteById(4L);
        List<Book> afterDeleteBooks = bookRepository.findAll();
        Assertions.assertThat(beforeBooks.size() - 1).isEqualTo(afterDeleteBooks.size());
    }

    @DisplayName("должен получать книгу по id")
    @Test
    void shouldGetBookById() {
        Book book = bookRepository.getById(1L);

        List<Author> authors = Collections.singletonList(new Author(1L, "Eric Matthes"));
        List<Genre> genres = Collections.singletonList(new Genre(1L, "Programming"));

        Book expectedBook = Book.builder()
                .id(1L)
                .title("Python Crash Course, 2nd Edition: A Hands-On, Project-Based Introduction to Programming")
                .authors(authors)
                .genres(genres)
                .build();


        Assertions.assertThat(expectedBook).isEqualTo(book);
    }

    @DisplayName("должен получать книги автора")
    @Test
    void shouldGetBooksByAuthorId() {
        List<Book> actualBooks = bookRepository.getByAuthorsId(1L);

        List<Book> expectedBooks = new ArrayList<>();

        List<Author> authors = Collections.singletonList(new Author(1L, "Eric Matthes"));
        List<Genre> genres = Collections.singletonList(new Genre(1L, "Programming"));

        Book firstBook = Book.builder()
                .id(1L)
                .title("Python Crash Course, 2nd Edition: A Hands-On, Project-Based Introduction to Programming")
                .authors(authors)
                .genres(genres)
                .build();
        expectedBooks.add(firstBook);

        Book secondBook = Book.builder()
                .id(2L)
                .title("Python Flash Cards: Syntax, Concepts, and Examples Cards")
                .authors(authors)
                .genres(genres)
                .build();

        expectedBooks.add(secondBook);

        Assertions.assertThat(expectedBooks).isEqualTo(actualBooks);
    }

}