package ru.otus.library.repository.book;

import ru.otus.library.domain.entity.Book;

public interface BookRepository {
    Book createBook(String title);
}
