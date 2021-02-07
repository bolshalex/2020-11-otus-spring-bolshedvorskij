package ru.otus.library.service;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.library.domain.dto.AuthorDto;
import ru.otus.library.domain.dto.BookDto;
import ru.otus.library.domain.dto.GenreDto;
import ru.otus.library.domain.entity.Author;
import ru.otus.library.domain.entity.Book;
import ru.otus.library.domain.entity.BookInfo;
import ru.otus.library.domain.entity.Genre;
import ru.otus.library.repository.author.AuthorRepository;
import ru.otus.library.repository.book.BookRepository;
import ru.otus.library.repository.genre.GenreRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository,
                           AuthorRepository authorRepository, GenreRepository genreRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    public BookDto createBook(String bookTitle, List<Long> authorIds, List<Long> genreIds) {
        BookInfo bookInfo = BookInfo.builder()
                .book(Book.builder().title(bookTitle).build())
                .authors(createAuthors(authorIds))
                .genres(createGenres(genreIds))
                .build();

        Book book = bookRepository.createBook(bookInfo);

        List<AuthorDto> authors = loadBookAuthors(book);
        List<GenreDto> genres = loadBookGenres(book);

        return BookDto.builder()
                .bookId(book.getId())
                .bookTitle(book.getTitle())
                .authors(authors)
                .genres(genres)
                .build();
    }

    private List<GenreDto> loadBookGenres(Book book) {
        List<Genre> genres = genreRepository.getByBookId(book.getId());
        List<GenreDto> genreDtoList = new ArrayList<>();
        if (CollectionUtils.isEmpty(genres)) {
            return genreDtoList;
        }
        for (Genre genre : genres) {
            genreDtoList.add(new GenreDto(genre.getId(), genre.getName()));
        }
        return genreDtoList;
    }

    private List<AuthorDto> loadBookAuthors(Book book) {
        List<Author> authors = authorRepository.getByBookId(book.getId());
        List<AuthorDto> authorDtoList = new ArrayList<>();

        if (CollectionUtils.isEmpty(authors)) {
            return authorDtoList;
        }
        for (Author author : authors) {
            authorDtoList.add(new AuthorDto(author.getId(), author.getName()));
        }
        return authorDtoList;
    }

    private List<Genre> createGenres(List<Long> genreIds) {
        List<Genre> genres = new ArrayList<>();

        for (Long genreId : genreIds) {
            Genre genre = Genre.builder().id(genreId).build();
            genres.add(genre);
        }
        return genres;
    }

    private List<Author> createAuthors(List<Long> authorIds) {
        List<Author> authors = new ArrayList<>();

        for (Long authorId : authorIds) {
            Author author = Author.builder().id(authorId).build();
            authors.add(author);
        }
        return authors;
    }

    @Override
    public void addBookAuthors(Long bookId, List<Long> authorIds) {


    }

    @Override
    public void addBookGenres(Long bookId, List<Long> genreIds) {

    }

    @Override
    public void updateBook(Long bookId, String title, List<Long> authorIds, List<Long> genreIds) {
        List<Author> authors = new ArrayList<>();
        for (Long authorId : authorIds) {
            authors.add(new Author(authorId));
        }

        List<Genre> genres = new ArrayList<>();
        for (Long genreId : genreIds) {
            genres.add(new Genre(genreId));
        }

        BookInfo bookInfo = BookInfo.builder()
                .book(new Book(bookId, title))
                .genres(genres)
                .authors(authors)
                .build();

        bookRepository.updateBook(bookInfo);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteBook(id);
    }

    @Override
    public BookDto getById(Long id) {
        Book book = bookRepository.getById(id);
        return BookDto.builder()
                .bookId(book.getId())
                .bookTitle(book.getTitle())
                .authors(loadBookAuthors(book))
                .genres(loadBookGenres(book))
                .build();
    }

    @Override
    public List<BookDto> getByAuthorId(Long authorId) {
        List<Book> books = bookRepository.getByAuthorId(authorId);
        return loadBookInfos(books);
    }

    private BookDto loadBookInfo(Book book) {
        List<AuthorDto> authorDtoList = loadBookAuthors(book);
        List<GenreDto> genreDtoList = loadBookGenres(book);
        return BookDto.builder()
                .bookId(book.getId())
                .bookTitle(book.getTitle())
                .authors(authorDtoList)
                .genres(genreDtoList)
                .build();
    }

    @Override
    public List<BookDto> getAll() {
        List<Book> books = bookRepository.getAll();
        return loadBookInfos(books);
    }

    private List<BookDto> loadBookInfos(List<Book> books) {

        List<BookDto> bookDtoList = new ArrayList<>();
        if (CollectionUtils.isEmpty(books)) {
            return bookDtoList;
        }

        for (Book book : books) {
            BookDto bookDto = loadBookInfo(book);
            bookDtoList.add(bookDto);
        }

        return bookDtoList;
    }
}
