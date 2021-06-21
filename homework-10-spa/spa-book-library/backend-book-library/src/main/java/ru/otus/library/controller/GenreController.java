package ru.otus.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.otus.library.domain.dto.GenreDto;
import ru.otus.library.service.genre.GenreService;

import java.util.List;

@RestController
public class GenreController {
    private final GenreService genreService;


    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }


    @GetMapping("/api/genres")
    public List<GenreDto> getGenres() {
        return genreService.getAllGenres();
    }

    @GetMapping("/api/genres/{id}")
    public GenreDto getGenre(@PathVariable("id") String genreId) {
        return genreService.getGenre(genreId);
    }

    @PostMapping("/api/genres")
    public void createGenre(@RequestBody GenreDto genre) {
        genreService.createGenre(genre);
    }

    @PutMapping("/api/genres")
    public void updateGenre(@RequestBody GenreDto genre) {
        genreService.updateGenre(genre);
    }

    @DeleteMapping("/api/genres/{id}")
    public void deleteGenre(@PathVariable("id") String genreId) {
        genreService.deleteGenre(genreId);
    }


}
