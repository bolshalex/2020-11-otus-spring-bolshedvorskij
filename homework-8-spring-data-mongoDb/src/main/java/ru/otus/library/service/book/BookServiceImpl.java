package ru.otus.library.service.book;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.library.domain.dto.AuthorDto;
import ru.otus.library.domain.dto.BookDto;
import ru.otus.library.domain.dto.GenreDto;
import ru.otus.library.domain.entity.Author;
import ru.otus.library.domain.entity.Book;
import ru.otus.library.domain.entity.Genre;
import ru.otus.library.repository.book.BookRepository;
import ru.otus.library.repository.comment.CommentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;


    @Autowired
    public BookServiceImpl(BookRepository bookRepository, CommentRepository commentRepository) {
        this.bookRepository = bookRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public BookDto createBook(String bookTitle, List<String> authorIds, List<String> genreIds) {
        Book book = Book.builder()
                .title(bookTitle)
                .authors(convertToAuthors(authorIds))
                .genres(convertToGenres(genreIds))
                .build();

        Book createdBook = bookRepository.save(book);

        return buildBookDto(createdBook);
    }

    @Override
    public void updateBook(String bookId, String title, List<String> authorIds, List<String> genreIds) {

        Book book = Book.builder()
                .id(bookId)
                .title(title)
                .genres(convertToGenres(genreIds))
                .authors(convertToAuthors(authorIds))
                .build();

        bookRepository.save(book);
    }

    @Override
    public void deleteBook(String id) {
        commentRepository.deleteAllByBookId(id);
        bookRepository.deleteById(id);
    }

    @Override
    public BookDto getById(String id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            return buildBookDto(bookOptional.get());
        }
        return new BookDto();
    }

    @Override
    public List<BookDto> getByAuthorId(String authorId) {
        List<Book> books = bookRepository.getByAuthorsId(authorId);
        return buildBookDtoList(books);
    }

    @Override
    public List<BookDto> getAll() {
        List<Book> books = bookRepository.findAll();
        return buildBookDtoList(books);
    }

    private List<Genre> convertToGenres(List<String> genreIds) {
        List<Genre> genres = new ArrayList<>();
        if (CollectionUtils.isEmpty(genreIds)) {
            return genres;
        }

        for (String genreId : genreIds) {
            genres.add(new Genre(genreId));
        }

        return genres;
    }

    private List<Author> convertToAuthors(List<String> authorIds) {
        List<Author> authors = new ArrayList<>();

        if (CollectionUtils.isEmpty(authorIds)) {
            return authors;
        }
        for (String authorId : authorIds) {
            authors.add(new Author(authorId));
        }
        return authors;
    }

    private List<BookDto> buildBookDtoList(List<Book> books) {

        List<BookDto> bookDtoList = new ArrayList<>();
        if (CollectionUtils.isEmpty(books)) {
            return bookDtoList;
        }

        for (Book book : books) {
            BookDto bookDto = buildBookDto(book);
            bookDtoList.add(bookDto);
        }

        return bookDtoList;
    }

    private BookDto buildBookDto(Book book) {
        if (book == null) {
            return new BookDto();
        }
        List<GenreDto> genreDtoList = buildGenreDtoList(book.getGenres());
        List<AuthorDto> authorDtoList = buildAuthorDtoList(book.getAuthors());

        return BookDto.builder()
                .bookId(book.getId())
                .bookTitle(book.getTitle())
                .genres(genreDtoList)
                .authors(authorDtoList)
                .build();
    }

    private List<AuthorDto> buildAuthorDtoList(List<Author> authors) {
        return authors.stream()
                .map(author -> new AuthorDto(author.getId(), author.getName()))
                .collect(Collectors.toList());
    }

    private List<GenreDto> buildGenreDtoList(List<Genre> genres) {
        return genres.stream()
                .map(genre -> new GenreDto(genre.getId(), genre.getName()))
                .collect(Collectors.toList());
    }
}
