package ru.otus.library.repository.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.library.domain.entity.BookComment;

import java.util.List;

public interface CommentRepository extends JpaRepository<BookComment, Long> {


    BookComment getById(Long id);

    List<BookComment> getByBookId(Long bookId);

    void deleteById(Long id);
}
