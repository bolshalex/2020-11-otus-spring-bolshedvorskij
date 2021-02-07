package ru.otus.library.domain.entity;

import lombok.Data;

import java.util.List;

@Data
public class Book {
    private final String title;
    private final List<Genre> genres;
    private final List<Author> authors;
}
