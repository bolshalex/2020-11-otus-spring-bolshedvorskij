package ru.otus.library.repository.book;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.library.domain.entity.Author;
import ru.otus.library.domain.entity.Book;
import ru.otus.library.domain.entity.Genre;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@DisplayName("Тест для работы с книгами")
@DataMongoTest
@EnableMongock
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @DisplayName("должен создавать книгу")
    @Test
    void createBook() {
        Book book = Book.builder().title("On Lisp: Advanced Techniques for Common Lisp").build();
        Book createdBook = bookRepository.save(book);
        Assertions.assertThat(book.getTitle()).isEqualTo(createdBook.getTitle());
    }

    @DisplayName("должен обновлять книгу")
    @Test
    void shouldUpdateBook() {

        List<Author> authors = Collections.singletonList(new Author("3", "Erich Gamma"));
        List<Genre> genres = Collections.singletonList(new Genre("2", "Computer Science"));

        Book book = Book.builder()
                .id("4")
                .title("Design Patterns: Elements of Reusable Object-Oriented Software 1st Edition")
                .authors(authors)
                .genres(genres)
                .build();

        bookRepository.save(book);
        Optional<Book> updatedBook = bookRepository.findById(book.getId());

        Assertions.assertThat(book).isEqualTo(updatedBook.get());
    }

    @DisplayName("должен удалять книгу")
    @Test
    void shouldDeleteBook() {
        List<Book> beforeBooks = bookRepository.findAll();
        bookRepository.deleteById("4");
        List<Book> afterDeleteBooks = bookRepository.findAll();
        Assertions.assertThat(beforeBooks.size() - 1).isEqualTo(afterDeleteBooks.size());
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("должен получать книгу по id")
    @Test
    void shouldGetBookById() {
        Optional<Book> book = bookRepository.findById("1");

        List<Author> authors = Collections.singletonList(new Author("1", "Eric Matthes"));
        List<Genre> genres = Collections.singletonList(new Genre("1", "Programming"));

        Book expectedBook = Book.builder()
                .id("1")
                .title("Python Crash Course, 2nd Edition: A Hands-On, Project-Based Introduction to Programming")
                .authors(authors)
                .genres(genres)
                .build();


        Assertions.assertThat(expectedBook).isEqualTo(book.get());
    }

    @DisplayName("должен получать книги автора")
    @Test
    void shouldGetBooksByAuthorId() {
        List<Book> actualBooks = bookRepository.getByAuthorsId("1");

        List<Book> expectedBooks = new ArrayList<>();

        List<Author> authors = Collections.singletonList(new Author("1", "Eric Matthes"));
        List<Genre> genres = Collections.singletonList(new Genre("1", "Programming"));

        Book firstBook = Book.builder()
                .id("1")
                .title("Python Crash Course, 2nd Edition: A Hands-On, Project-Based Introduction to Programming")
                .authors(authors)
                .genres(genres)
                .build();
        expectedBooks.add(firstBook);

        Book secondBook = Book.builder()
                .id("2")
                .title("Python Flash Cards: Syntax, Concepts, and Examples Cards")
                .authors(authors)
                .genres(genres)
                .build();

        expectedBooks.add(secondBook);

        Assertions.assertThat(expectedBooks).isEqualTo(actualBooks);
    }

}