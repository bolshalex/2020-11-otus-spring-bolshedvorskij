package ru.otus.library.repository.book;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.library.domain.entity.Author;
import ru.otus.library.domain.entity.Book;
import ru.otus.library.domain.entity.BookInfo;
import ru.otus.library.domain.entity.Genre;
import ru.otus.library.repository.author.AuthorRepository;
import ru.otus.library.repository.author.JdbcAuthorRepository;
import ru.otus.library.repository.genre.GenreRepository;
import ru.otus.library.repository.genre.JdbcGenreRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@DisplayName("Тест для работы с книгами")
@JdbcTest
@ExtendWith(SpringExtension.class)
@Import({JdbcBookRepository.class, JdbcAuthorRepository.class, JdbcGenreRepository.class})
class JdbcBookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private GenreRepository genreRepository;

    @DisplayName("должен создавать книгу")
    @Test
    void createBook() {
        Book book = Book.builder().title("On Lisp: Advanced Techniques for Common Lisp").build();
        BookInfo bookInfo = BookInfo.builder().book(book).build();
        Book createdBook = bookRepository.createBook(bookInfo);
        Assertions.assertThat(book.getTitle()).isEqualTo(createdBook.getTitle());
    }

    @DisplayName("должен обновлять книгу")
    @Test
    void shouldUpdateBook() {
        Book book = new Book(4L, "Design Patterns: Elements of Reusable Object-Oriented Software 1st Edition");

        List<Author> authors = Collections.singletonList(new Author(3L, "Erich Gamma"));
        List<Genre> genres = Collections.singletonList(new Genre(2L, "Computer Science"));
        BookInfo bookInfo = BookInfo.builder()
                .book(book)
                .authors(authors)
                .genres(genres)
                .build();

        bookRepository.updateBook(bookInfo);

        Book updatedBook = bookRepository.getById(book.getId());
        Assertions.assertThat(book).isEqualTo(updatedBook);

        List<Author> bookAuthors = authorRepository.getByBookId(book.getId());
        Assertions.assertThat(authors).isEqualTo(bookAuthors);

        List<Genre> bookGenres = genreRepository.getByBookId(book.getId());
        Assertions.assertThat(genres).isEqualTo(bookGenres);

    }

    @DisplayName("должен удалять книгу")
    @Test
    void shouldDeleteBook() {
        List<Book> beforeBooks = bookRepository.getAll();
        bookRepository.deleteBook(4L);
        List<Book> afterDeleteBooks = bookRepository.getAll();
        Assertions.assertThat(beforeBooks.size() - 1).isEqualTo(afterDeleteBooks.size());
    }

    @DisplayName("должен получать книгу по id")
    @Test
    void shouldGetById() {
        Book book = bookRepository.getById(1L);
        Book expectedBook = new Book(1L, "Python Crash Course, 2nd Edition: A Hands-On, Project-Based Introduction to Programming");
        Assertions.assertThat(book).isEqualTo(expectedBook);
    }

    @DisplayName("должен поучать книги автора")
    @Test
    void shouldGetByAuthorId() {
        List<Book> book = bookRepository.getByAuthorId(1L);

        List<Book> expectedBooks = new ArrayList<>();
        expectedBooks.add(new Book(1L, "Python Crash Course, 2nd Edition: A Hands-On, Project-Based Introduction to Programming"));
        expectedBooks.add(new Book(2L, "Python Flash Cards: Syntax, Concepts, and Examples Cards"));

        Assertions.assertThat(book).isEqualTo(expectedBooks);
    }
}