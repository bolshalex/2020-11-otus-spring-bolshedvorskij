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
    public AuthorDto createAuthor(String name, List<String> bookIds) {
        Author author = Author.builder()
                .name(name)
                .build();

        Author createdAuthor = authorRepository.save(author);
        addAuthorBooks(bookIds, author);

        return AuthorDto.builder()
                .id(createdAuthor.getId())
                .name(createdAuthor.getName())
                .build();
    }

    @Override
    public void updateAuthor(String authorId, String name, List<String> bookIds) {

        Author author = Author.builder()
                .id(authorId)
                .name(name)
                .build();
        addAuthorBooks(bookIds, author);
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
    public List<AuthorDto> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
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

    private void addAuthorBooks(List<String> bookIds, Author author) {
        if (CollectionUtils.isNotEmpty(bookIds)) {
            List<Book> books = bookIds.stream()
                    .map(Book::new)
                    .collect(Collectors.toList());
            bookRepository.putAuthorToBooks(author, books);
        }
    }

}
