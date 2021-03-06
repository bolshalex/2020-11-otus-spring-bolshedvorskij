package ru.otus.library.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.library.domain.dto.BookDto;
import ru.otus.library.service.book.BookService;
import ru.otus.library.utils.Formatter;
import ru.otus.library.utils.StringCollection;

import java.util.List;

@ShellComponent
public class BookShell {
    private final BookService bookService;
    private final Formatter jsonFormatter;

    @Autowired
    public BookShell(BookService bookService, Formatter jsonFormatter) {
        this.bookService = bookService;
        this.jsonFormatter = jsonFormatter;
    }


    @ShellMethod(value = "create book", key = {"cb", "createBook"})
    public String createBook(@ShellOption({"-t"}) String title,
                             @ShellOption(value = {"-a"}, defaultValue = ShellOption.NULL) String authorIds,
                             @ShellOption(value = {"-g"}, defaultValue = ShellOption.NULL) String genreIds) {
        BookDto book = bookService.createBook(title, StringCollection.splitLong(authorIds), StringCollection.splitLong(genreIds));
        return jsonFormatter.format(book);
    }

    @ShellMethod(value = "update book info", key = {"ub", "updateBook"})
    public String updateBook(@ShellOption(value = {"-id"}) Long bookId,
                             @ShellOption(value = {"-t"}) String title,
                             @ShellOption(value = {"-a"}, defaultValue = ShellOption.NULL) String authorIds,
                             @ShellOption(value = {"-g"}, defaultValue = ShellOption.NULL) String genresIds) {
        bookService.updateBook(bookId,
                title,
                StringCollection.splitLong(authorIds),
                StringCollection.splitLong(genresIds));
        return "Book updated";
    }

    @ShellMethod(value = "delete book", key = {"delBook"})
    public String deleteBook(@ShellOption(value = {"-id"}) Long bookId) {
        bookService.deleteBook(bookId);
        return "Book deleted";
    }

    @ShellMethod(value = "get book", key = {"getBook"})
    public String getBook(@ShellOption(value = {"-id"}) Long bookId) {
        BookDto book = bookService.getById(bookId);
        return jsonFormatter.format(book);
    }

    @ShellMethod(value = "get author's books", key = {"getAuthorBooks"})
    public String getAuthorBooks(@ShellOption(value = {"-id"}) Long authorId) {
        List<BookDto> books = bookService.getByAuthorId(authorId);
        return jsonFormatter.format(books);
    }

    @ShellMethod(value = "get all books", key = {"getBooks"})
    public String getAllBooks() {
        List<BookDto> books = bookService.getAll();
        return jsonFormatter.format(books);
    }


}
