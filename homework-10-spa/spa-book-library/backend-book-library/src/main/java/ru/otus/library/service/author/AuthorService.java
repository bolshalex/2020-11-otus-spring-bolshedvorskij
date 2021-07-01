package ru.otus.library.service.author;

import ru.otus.library.domain.dto.AuthorDto;

import java.util.List;

public interface AuthorService {
    AuthorDto createAuthor(AuthorDto author);

    void updateAuthor(AuthorDto author);

    void deleteAuthor(String authorId);

    AuthorDto getAuthor(String authorId);

    AuthorDto getAuthorByName(String name);

    List<AuthorDto> getAuthorsByName(String name);

    List<AuthorDto> getAllAuthors();

}
