package ru.otus.library.repository.comment;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.otus.library.domain.entity.Book;
import ru.otus.library.domain.entity.BookComment;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@DisplayName("тест для работы с комментариями ")
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class JpaCommentRepositoryTest {
    @Autowired
    private CommentRepository commentRepository;

    @DisplayName("должен создавать комментарий")
    @Test
    void shouldCreateComment() {
        Book book = Book.builder()
                .id(3L)
                .build();
        BookComment comment = BookComment.builder()
                .text("Очень интересно, но ничего не понятно")
                .book(book)
                .build();
        commentRepository.saveAndFlush(comment);
        BookComment createdComment = commentRepository.getById(5L);
        Assertions.assertThat(comment).isEqualTo(createdComment);
    }

    @DisplayName("должен обновлять комментарий")
    @Test
    void update() {
        Book book = Book.builder()
                .id(4L)
                .build();

        BookComment comment = BookComment.builder()
                .id(2L)
                .text("комментарий")
                .book(book)
                .build();

        commentRepository.saveAndFlush(comment);

        BookComment updatedComment = commentRepository.getById(comment.getId());
        Assertions.assertThat(comment).isEqualTo(updatedComment);
    }

    @DisplayName("должен получать комментарий по id")
    @Test
    void shouldGetCommentById() {
        Book book = Book.builder()
                .id(4L)
                .build();

        BookComment comment = BookComment.builder()
                .id(2L)
                .text("left comment")
                .book(book)
                .build();

        BookComment actualComment = commentRepository.getById(2L);
        Assertions.assertThat(comment).isEqualTo(actualComment);
    }

    @DisplayName("должен получать комментарии книги")
    @Test
    void shouldGetBookComments() {
        Book book = Book.builder()
                .id(4L)
                .build();

        BookComment firstComment = BookComment.builder()
                .id(2L)
                .text("left comment")
                .book(book)
                .build();

        BookComment secondComment = BookComment.builder()
                .id(3L)
                .text("recommend")
                .book(book)
                .build();

        List<BookComment> exceptedBookComments = Arrays.asList(firstComment, secondComment);

        List<BookComment> actualBookComment = commentRepository.getByBookId(book.getId());

        Assertions.assertThat(exceptedBookComments).containsExactlyInAnyOrderElementsOf(actualBookComment);

    }

    @DisplayName("должен удалять комментарий")
    @Test
    void shouldDeleteComment() {
        Book book = Book.builder()
                .id(4L)
                .build();

        BookComment comment = BookComment.builder()
                .id(2L)
                .text("left comment")
                .book(book)
                .build();

        List<BookComment> exceptedBookComments = Collections.singletonList(comment);
        commentRepository.deleteById(3L);

        List<BookComment> actualBookComment = commentRepository.getByBookId(book.getId());
        Assertions.assertThat(exceptedBookComments).containsExactlyInAnyOrderElementsOf(actualBookComment);
    }
}