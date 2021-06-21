package ru.otus.library.service.comment;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.library.domain.dto.BookCommentDto;
import ru.otus.library.domain.entity.Book;
import ru.otus.library.domain.entity.BookComment;
import ru.otus.library.repository.comment.CommentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public void createComment(String bookId, String text) {
        Book book = Book.builder().id(bookId).build();
        BookComment comment = BookComment.builder()
                .book(book)
                .text(text)
                .build();
        commentRepository.save(comment);

    }

    @Override
    public void update(String commentId, String text) {
        BookComment comment = BookComment.builder()
                .id(commentId)
                .text(text)
                .build();
        commentRepository.save(comment);

    }

    @Override
    public BookCommentDto getCommentById(String id) {
        Optional<BookComment> optionalBookComment = commentRepository.findById(id);
        if (optionalBookComment.isEmpty()) {
            return new BookCommentDto();
        }
        return buildCommentDto(optionalBookComment.get());
    }

    @Override
    public List<BookCommentDto> getBookComments(String bookId) {
        List<BookComment> comments = commentRepository.getByBookId(bookId);
        return buildCommentDtoList(comments);
    }

    @Override
    public void delete(String id) {
        commentRepository.deleteById(id);
    }

    private List<BookCommentDto> buildCommentDtoList(List<BookComment> bookComments) {
        List<BookCommentDto> commentDtoList = new ArrayList<>();
        if (CollectionUtils.isEmpty(bookComments)) {
            return commentDtoList;
        }

        for (BookComment bookComment : bookComments) {
            commentDtoList.add(buildCommentDto(bookComment));
        }

        return commentDtoList;
    }

    private BookCommentDto buildCommentDto(BookComment comment) {
        if (comment == null) {
            return new BookCommentDto();
        }

        return BookCommentDto.builder()
                .commentId(comment.getId())
                .text(comment.getText())
                .bookId(comment.getBook().getId())
                .build();
    }

}
