package ru.otus.library.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.library.domain.dto.BookCommentDto;
import ru.otus.library.service.comment.CommentService;
import ru.otus.library.utils.Formatter;

import java.util.List;

@ShellComponent
public class CommentShell {
    private final CommentService commentService;
    private final Formatter jsonFormatter;

    @Autowired
    public CommentShell(CommentService commentService, Formatter jsonFormatter) {
        this.commentService = commentService;
        this.jsonFormatter = jsonFormatter;
    }

    @ShellMethod(value = "add book comment", key = {"abc", "addComment"})
    public String createComment(@ShellOption({"-bookId"}) String bookId,
                                @ShellOption({"-t"}) String text) {
        commentService.createComment(bookId, text);
        return "Comment created";
    }

    @ShellMethod(value = "update comment", key = "uc")
    public String update(@ShellOption({"-id"}) String commentId,
                         @ShellOption({"-t"}) String text) {
        commentService.update(commentId, text);
        return "Comment updated";
    }

    @ShellMethod(value = "get comment", key = {"getComment", "gc"})
    public String getCommentById(@ShellOption({"-id"}) String id) {
        BookCommentDto bookComment = commentService.getCommentById(id);
        return jsonFormatter.format(bookComment);
    }

    @ShellMethod(value = "get book comments", key = {"getBookComments", "gbc"})
    public String getBookComments(@ShellOption({"-bookId"}) String bookId) {
        List<BookCommentDto> comments = commentService.getBookComments(bookId);
        return jsonFormatter.format(comments);
    }

    @ShellMethod(value = "delete comment", key = {"dc", "deleteComment"})
    public String deleteComment(@ShellOption({"-id"}) String id) {
        commentService.delete(id);
        return "Comment deleted";
    }
}
