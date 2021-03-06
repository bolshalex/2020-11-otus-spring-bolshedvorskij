package ru.otus.library.service.comment;

import ru.otus.library.domain.dto.BookCommentDto;

import java.util.List;

public interface CommentService {

    void createComment(Long bookId, String text);

    void update(Long commentId, String text);

    BookCommentDto getCommentById(Long id);

    List<BookCommentDto> getBookComments(Long bookId);

    void delete(Long id);

}
