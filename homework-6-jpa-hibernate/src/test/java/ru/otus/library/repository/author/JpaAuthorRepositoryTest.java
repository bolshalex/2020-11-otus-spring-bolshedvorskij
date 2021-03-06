package ru.otus.library.repository.author;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.library.domain.entity.Author;
import ru.otus.library.domain.entity.Book;
import ru.otus.library.domain.entity.Genre;
import ru.otus.library.repository.book.BookRepository;
import ru.otus.library.repository.book.JpaBookRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@DisplayName("Тест для работы с авторами")
@DataJpaTest
@Import({JpaAuthorRepository.class, JpaBookRepository.class})
class JpaAuthorRepositoryTest {

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

        Author actualAuthor = authorRepository.createAuthor(author);
        Assertions.assertThat(actualAuthor.getName()).isEqualTo(exceptedAuthor.getName());
    }

    @DisplayName("должен обновлять автора")
    @Test
    void shouldUpdateAuthor() {
        Author expectedAuthor = new Author(1L, "Eric");
        authorRepository.updateAuthor(expectedAuthor);
        Author actualAuthor = authorRepository.getById(1L);
        Assertions.assertThat(actualAuthor).isEqualTo(expectedAuthor);
    }

    @DisplayName("должен обновлять список книг автора")
    @Test
    void shouldUpdateAuthorBooks() {
        List<Book> books = Collections.singletonList(Book.builder().id(1L).build());
        Author author = new Author(1L, "Eric Matthes");
        author.setBooks(books);

        authorRepository.updateAuthor(author);

        List<Book> actualBook = bookRepository.getByAuthorId(author.getId());

        Book expectedBook = Book.builder()
                .id(1L)
                .title("Python Crash Course, 2nd Edition: A Hands-On, Project-Based Introduction to Programming")
                .authors(Collections.singletonList(author))
                .genres(Collections.singletonList(new Genre(1L, "Programming")))
                .build();

        List<Book> expectedBooks = Collections.singletonList(expectedBook);

        Assertions.assertThat(expectedBooks).isEqualTo(actualBook);
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

    @DisplayName("должен получать список авторов по id книги ")
    @Test
    void shouldGetAuthorsByBookId() {
        List<Author> expectedAuthors = new ArrayList<>();
        expectedAuthors.add(new Author(3L, "Erich Gamma"));
        expectedAuthors.add(new Author(4L, "Richard Helm"));
        expectedAuthors.add(new Author(5L, "Ralph Johnson"));
        expectedAuthors.add(new Author(6L, "John Vlissides"));
        expectedAuthors.add(new Author(7L, "Grady Booch"));

        List<Author> actualAuthors = authorRepository.getByBookId(4L);
        Assertions.assertThat(expectedAuthors)
                .containsExactlyInAnyOrderElementsOf(actualAuthors);
    }
}