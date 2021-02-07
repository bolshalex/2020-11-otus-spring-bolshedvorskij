package ru.otus.library.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class AuthorShell {

    @ShellMethod(value = "add author", key = {"addAuthor"})
    public String addAuthor(@ShellOption(value = {"n", "name"}) String name,
                            @ShellOption(value = {"b", "books"}) String bookIds) {
        return "Author added";
    }

    @ShellMethod(value = "update author info", key = {"ua", "updateAuthor"})
    public String updateAuthor(@ShellOption(value = {"id"}) Long authorId,
                               @ShellOption(value = {"n", "name"}) String name,
                               @ShellOption(value = {"b"}, defaultValue = ShellOption.NULL) String bookIds) {
        return "Author updated";
    }

    @ShellMethod(value = "delete author")
    public String deleteAuthor(@ShellOption(value = {"id"}) Long authorId) {

        return "";
    }

    @ShellMethod(value = "get author", key = {"getAuthor"})
    public String getAuthor(@ShellOption(value = {"id"}) Long authorId) {
        return "";
    }

    @ShellMethod(value = "get authors", key = {"getAuthors"})
    public String getAllAuthors() {
        return "";
    }

}
