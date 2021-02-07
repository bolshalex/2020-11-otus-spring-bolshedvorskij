package ru.otus.library.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.library.domain.dto.GenreDto;
import ru.otus.library.service.genre.GenreService;

import java.util.List;

@ShellComponent
public class GenreShell {
    private final GenreService genreService;

    @Autowired
    public GenreShell(GenreService genreService) {
        this.genreService = genreService;
    }


    @ShellMethod(value = "Add genre", key = "createGenre", prefix = "-")
    public String createGenre(@ShellOption() String name) {
        GenreDto genre = genreService.createGenre(name);
        return genre.toString();
    }

    @ShellMethod(value = "update genre", key = {"updateGenre", "ug"}, prefix = "-")
    public String updateGenre(@ShellOption Long id,
                              @ShellOption({"-n"}) String name) {
        genreService.updateGenre(id, name);
        return "Genre updated";
    }

    @ShellMethod(value = "delete genre", key = {"dg", "delGenre"}, prefix = "-")
    public String deleteGenre(@ShellOption Long id) {
        genreService.deleteGenre(id);
        return "genre deleted";
    }

    @ShellMethod(value = "get genre", key = "getGenre", prefix = "-")
    public String getGenre(@ShellOption Long id) {
        GenreDto genre = genreService.getGenre(id);
        return genre.toString();
    }

    @ShellMethod(value = "get all genres", key = "getGenres")
    public String getAllGenres() {
        List<GenreDto> genres = genreService.getAllGenres();
        return genres.toString();
    }
}
