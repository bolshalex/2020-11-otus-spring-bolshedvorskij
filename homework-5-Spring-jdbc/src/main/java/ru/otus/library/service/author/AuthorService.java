package ru.otus.library.service.author;

import ru.otus.library.domain.dto.AuthorDto;

import java.util.List;

public interface AuthorService {
    AuthorDto createAuthor(String name, List<Long> bookIds);

    void updateAuthor(Long authorId, String name, List<Long> bookIds);

    void deleteAuthor(Long authorId);

    AuthorDto getAuthor(Long authorId);

    AuthorDto getAuthorByName(String name);

    List<AuthorDto> getAllAuthors();

}
