package ru.otus.library.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class BookShell {


    @ShellMethod(value = "add book", key = {"ab", "addBook"})
    public String addBook(@ShellOption(value = {"t"}) String title,
                          @ShellOption(value = {"a"}, defaultValue = ShellOption.NULL) String authorIds,
                          @ShellOption(value = {"g"}, defaultValue = ShellOption.NULL) String genreIds) {

        return "Book added";
    }

    @ShellMethod(value = "update book info", key = {"ub", "updateBook"})
    public String updateBook(@ShellOption(value = {"id"}) Long bookId,
                             @ShellOption(value = {"t"}) String title,
                             @ShellOption(value = {"a"}, defaultValue = ShellOption.NULL) String authorIds,
                             @ShellOption(value = {"g"}, defaultValue = ShellOption.NULL) String genresIds) {
        return "Book updated";
    }

    @ShellMethod(value = "delete book", key = {"delBook"})
    public String deleteBook(@ShellOption(value = {"id"}) Long bookId) {
        return "Book deleted";
    }

    @ShellMethod(value = "get book", key = {"getBook"})
    public String getBook(@ShellOption(value = {"id"}) Long bookId) {

        return "";
    }

    @ShellMethod(value = "get author's books", key = {"getAuthorBooks"})
    public String getAuthorBooks(@ShellOption(value = {"id"}) Long authorId) {

        return "";
    }

    @ShellMethod(value = "get all books", key = {"getBooks"})
    public String getAllBooks() {
        return "";
    }


}
