package ru.otus.library.service.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.library.domain.dto.AuthorDto;
import ru.otus.library.domain.entity.Author;
import ru.otus.library.domain.entity.AuthorInfo;
import ru.otus.library.domain.entity.Book;
import ru.otus.library.repository.author.AuthorRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorDto createAuthor(String name, List<Long> bookIds) {
        Author author = Author.builder()
                .name(name)
                .build();

        AuthorInfo authorInfo = createAuthorInfo(author, bookIds);
        Author createdAuthor = authorRepository.createAuthor(authorInfo);

        return AuthorDto.builder()
                .id(createdAuthor.getId())
                .name(createdAuthor.getName())
                .build();
    }

    @Override
    public void updateAuthor(Long authorId, String name, List<Long> bookIds) {
        Author author = Author.builder()
                .id(authorId)
                .name(name)
                .build();
        AuthorInfo authorInfo = createAuthorInfo(author, bookIds);
        authorRepository.updateAuthor(authorInfo);
    }

    @Override
    public void deleteAuthor(Long authorId) {
        authorRepository.deleteAuthor(authorId);
    }

    @Override
    public AuthorDto getAuthor(Long authorId) {
        Author author = authorRepository.getById(authorId);
        return AuthorDto.builder()
                .id(authorId)
                .name(author.getName())
                .build();
    }

    @Override
    public AuthorDto getAuthorByName(String name) {
        Author author = authorRepository.getByName(name);
        return AuthorDto.builder()
                .id(author.getId())
                .name(author.getName())
                .build();
    }

    @Override
    public List<AuthorDto> getAllAuthors() {
        List<Author> authors = authorRepository.getAll();
        List<AuthorDto> authorDtoList = new ArrayList<>();
        for (Author author : authors) {
            AuthorDto authorDto = AuthorDto.builder()
                    .id(author.getId())
                    .name(author.getName())
                    .build();
            authorDtoList.add(authorDto);
        }
        return authorDtoList;
    }

    private AuthorInfo createAuthorInfo(Author author, List<Long> bookIds) {
        List<Book> books = new ArrayList<>();
        for (Long bookId : bookIds) {
            Book book = Book.builder().id(bookId).build();
            books.add(book);
        }
        return new AuthorInfo(author, books);
    }
}
