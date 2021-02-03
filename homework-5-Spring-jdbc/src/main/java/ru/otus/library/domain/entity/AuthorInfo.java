package ru.otus.library.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AuthorInfo {
    private Author author;
    private List<Book> books;
}
