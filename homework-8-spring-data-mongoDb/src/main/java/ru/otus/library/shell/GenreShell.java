package ru.otus.library.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.library.domain.dto.GenreDto;
import ru.otus.library.service.genre.GenreService;
import ru.otus.library.utils.Formatter;

import java.util.List;

@ShellComponent
public class GenreShell {
    private final GenreService genreService;
    private final Formatter jsonFormatter;

    @Autowired
    public GenreShell(GenreService genreService, Formatter jsonFormatter) {
        this.genreService = genreService;
        this.jsonFormatter = jsonFormatter;
    }


    @ShellMethod(value = "Add genre", key = "createGenre", prefix = "-")
    public String createGenre(@ShellOption() String name) {
        GenreDto genre = genreService.createGenre(name);
        return jsonFormatter.format(genre);
    }

    @ShellMethod(value = "update genre", key = {"updateGenre", "ug"}, prefix = "-")
    public String updateGenre(@ShellOption String id,
                              @ShellOption({"-n"}) String name) {
        genreService.updateGenre(id, name);
        return "Genre updated";
    }

    @ShellMethod(value = "delete genre", key = {"dg", "delGenre"}, prefix = "-")
    public String deleteGenre(@ShellOption String id) {
        genreService.deleteGenre(id);
        return "genre deleted";
    }

    @ShellMethod(value = "get genre", key = "getGenre", prefix = "-")
    public String getGenre(@ShellOption String id) {
        GenreDto genre = genreService.getGenre(id);
        return jsonFormatter.format(genre);
    }

    @ShellMethod(value = "get all genres", key = "getGenres")
    public String getAllGenres() {
        List<GenreDto> genres = genreService.getAllGenres();
        return jsonFormatter.format(genres);
    }
}
