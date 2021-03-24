package ru.otus.library.service.comment;

import ru.otus.library.domain.dto.BookCommentDto;

import java.util.List;

public interface CommentService {

    void createComment(String bookId, String text);

    void update(String commentId, String text);

    BookCommentDto getCommentById(String id);

    List<BookCommentDto> getBookComments(String bookId);

    void delete(String id);

}
