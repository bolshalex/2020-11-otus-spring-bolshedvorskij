package ru.otus.library.service.author;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.library.domain.dto.AuthorDto;
import ru.otus.library.domain.entity.Author;
import ru.otus.library.domain.entity.Book;
import ru.otus.library.repository.author.AuthorRepository;
import ru.otus.library.repository.book.BookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public AuthorDto createAuthor(AuthorDto authorDto) {
        Author author = Author.builder()
                .name(authorDto.getName())
                .build();

        Author createdAuthor = authorRepository.save(author);

        return AuthorDto.builder()
                .id(createdAuthor.getId())
                .name(createdAuthor.getName())
                .build();
    }

    @Override
    public void updateAuthor(AuthorDto authorDto) {

        Author author = Author.builder()
                .id(authorDto.getId())
                .name(authorDto.getName())
                .build();

        authorRepository.save(author);

    }

    @Override
    public void deleteAuthor(String authorId) {
        authorRepository.deleteById(authorId);
    }

    @Override
    public AuthorDto getAuthor(String authorId) {
        Optional<Author> authorOptional = authorRepository.findById(authorId);
        if (authorOptional.isEmpty()) {
            return new AuthorDto();
        }

        return AuthorDto.builder()
                .id(authorId)
                .name(authorOptional.get().getName())
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
    public List<AuthorDto> getAuthorsByName(String name) {
        List<Author> authors = authorRepository.getAllByNameLike(name);
        return toAuthorDtoList(authors);
    }

    @Override
    public List<AuthorDto> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        return toAuthorDtoList(authors);
    }

    private void addAuthorBooks(List<String> bookIds, Author author) {
        if (CollectionUtils.isNotEmpty(bookIds)) {
            List<Book> books = bookIds.stream()
                    .map(Book::new)
                    .collect(Collectors.toList());
            bookRepository.putAuthorToBooks(author, books);
        }
    }

    private List<AuthorDto> toAuthorDtoList(List<Author> authorsList) {
        if (CollectionUtils.isEmpty(authorsList)) {
            return new ArrayList<>();
        }
        return authorsList
                .stream()
                .map(author -> new AuthorDto(author.getId(), author.getName()))
                .collect(Collectors.toList());

    }

}
