package ru.otus.library.repository.comment;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.library.domain.entity.Book;
import ru.otus.library.domain.entity.BookComment;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@DisplayName("тест для работы с комментариями ")
@DataMongoTest
@EnableMongock
class CommentRepositoryTest {
    @Autowired
    private CommentRepository commentRepository;

    @DisplayName("должен создавать комментарий")
    @Test
    void shouldCreateComment() {
        Book book = Book.builder()
                .id("3")
                .build();

        BookComment comment = BookComment.builder()
                .text("Очень интересно, но ничего не понятно")
                .book(book)
                .build();

        BookComment bookComment = commentRepository.save(comment);

        Optional<BookComment> actualComment = commentRepository.findById(bookComment.getId());

        Assertions.assertThat(comment).isEqualTo(actualComment.get());
    }

    @DisplayName("должен обновлять комментарий")
    @Test
    void update() {
        Book book = Book.builder()
                .id("1")
                .build();

        BookComment comment = BookComment.builder()
                .id("4")
                .text("комментарий")
                .book(book)
                .build();

        commentRepository.save(comment);

        Optional<BookComment> updatedComment = commentRepository.findById(comment.getId());
        Assertions.assertThat(comment).isEqualTo(updatedComment.get());
    }

    @DisplayName("должен получать комментарий по id")
    @Test
    void shouldGetCommentById() {
        BookComment comment = BookComment.builder()
                .id("2")
                .text("left comment")
                .build();

        Optional<BookComment> actualComment = commentRepository.findById("2");

        Assertions.assertThat(comment).isEqualTo(actualComment.get());
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("должен получать комментарии книги")
    @Test
    void shouldGetBookComments() {
        Book book = Book.builder()
                .id("4")
                .build();

        BookComment firstComment = BookComment.builder()
                .id("2")
                .text("left comment")
                .book(book)
                .build();

        BookComment secondComment = BookComment.builder()
                .id("3")
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
                .id("4")
                .build();

        BookComment comment = BookComment.builder()
                .id("2")
                .text("left comment")
                .book(book)
                .build();

        List<BookComment> exceptedBookComments = Collections.singletonList(comment);
        commentRepository.deleteById("3");

        List<BookComment> actualBookComment = commentRepository.getByBookId(book.getId());
        commentRepository.save(comment);
        Assertions.assertThat(exceptedBookComments).containsExactlyInAnyOrderElementsOf(actualBookComment);
    }
}