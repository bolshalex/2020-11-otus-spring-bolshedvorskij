package ru.otus.library.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
public class BookInfo {
    private Book book;
    private List<Author> authors;
    private List<Genre> genres;
}
