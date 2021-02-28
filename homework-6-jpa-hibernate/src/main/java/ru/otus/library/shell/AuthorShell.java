package ru.otus.library.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.library.domain.dto.AuthorDto;
import ru.otus.library.service.author.AuthorService;
import ru.otus.library.utils.Formatter;
import ru.otus.library.utils.StringCollection;

import java.util.List;

@ShellComponent
public class AuthorShell {
    private final AuthorService authorService;
    private final Formatter jsonFormatter;

    @Autowired
    public AuthorShell(AuthorService authorService, Formatter jsonFormatter) {
        this.authorService = authorService;
        this.jsonFormatter = jsonFormatter;
    }

    @ShellMethod(value = "create author", key = {"createAuthor"})
    public String createAuthor(@ShellOption(value = {"-n", "-name"}) String name,
                               @ShellOption(value = {"-b", "-books"}, defaultValue = ShellOption.NULL) String bookIds) {
        AuthorDto author = authorService.createAuthor(name, StringCollection.splitLong(bookIds));
        return jsonFormatter.format(author);
    }

    @ShellMethod(value = "update author info", key = {"ua", "updateAuthor"})
    public String updateAuthor(@ShellOption(value = {"-id"}) Long authorId,
                               @ShellOption(value = {"-n", "name"}) String name,
                               @ShellOption(value = {"-b"}, defaultValue = ShellOption.NULL) String bookIds) {
        authorService.updateAuthor(authorId, name, StringCollection.splitLong(bookIds));
        return "Author updated";
    }

    @ShellMethod(value = "delete author")
    public String deleteAuthor(@ShellOption(value = {"-id"}) Long authorId) {
        authorService.deleteAuthor(authorId);
        return "Author deleted";
    }

    @ShellMethod(value = "get author", key = {"getAuthor"})
    public String getAuthor(@ShellOption(value = {"-id"}) Long authorId) {
        AuthorDto author = authorService.getAuthor(authorId);
        return jsonFormatter.format(author);
    }

    @ShellMethod(value = "get authors", key = {"getAuthors"})
    public String getAllAuthors() {
        List<AuthorDto> authors = authorService.getAllAuthors();
        return jsonFormatter.format(authors);
    }

}
