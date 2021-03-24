package ru.otus.library.service.author;

import ru.otus.library.domain.dto.AuthorDto;

import java.util.List;

public interface AuthorService {
    AuthorDto createAuthor(String name, List<String> bookIds);

    void updateAuthor(String authorId, String name, List<String> bookIds);

    void deleteAuthor(String authorId);

    AuthorDto getAuthor(String authorId);

    AuthorDto getAuthorByName(String name);

    List<AuthorDto> getAllAuthors();

}
