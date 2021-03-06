package ru.otus.library.repository.comment;

import ru.otus.library.domain.entity.BookComment;

import java.util.List;

public interface CommentRepository {

    void createComment(BookComment comment);

    void update(BookComment comment);

    BookComment getCommentById(Long id);

    List<BookComment> getBookComments(Long bookId);

    void delete(Long id);


}
