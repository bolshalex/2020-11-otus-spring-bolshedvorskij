package ru.otus.library.repository.comment;

import org.springframework.data.repository.CrudRepository;
import ru.otus.library.domain.entity.BookComment;

import java.util.List;

public interface CommentRepository extends CrudRepository<BookComment, String> {

    List<BookComment> getByBookId(String bookId);

    void deleteAllByBookId(String bookId);

}
