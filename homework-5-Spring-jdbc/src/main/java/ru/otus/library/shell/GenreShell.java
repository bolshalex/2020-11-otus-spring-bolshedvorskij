package ru.otus.library.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class GenreShell {

    @ShellMethod(value = "Add genre", key = "addGenre")
    public String addGenre(@ShellOption() String name) {
        return "Genre added";
    }

    @ShellMethod(value = "update genre", key = "update")
    public String updateGenre(@ShellOption Long id,
                              @ShellOption String name) {

        return "Genre updated";
    }

    @ShellMethod(value = "delete genre", key = {"dg", "delGenre"})
    public String deleteGenre(@ShellOption Long id) {

        return "genre deleted";
    }

    @ShellMethod(value = "get genre", key = "getGenre")
    public String getGenre(@ShellOption Long id) {
        return "";
    }

    @ShellMethod(value = "get all genres", key = "getGenres")
    public String getAllGenres() {
        return "";
    }
}
