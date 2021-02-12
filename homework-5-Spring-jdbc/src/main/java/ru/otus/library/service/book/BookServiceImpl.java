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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookDto createBook(String bookTitle, List<Long> authorIds, List<Long> genreIds) {
        Book book = Book.builder()
                .title(bookTitle)
                .authors(buildAuthorList(authorIds))
                .genres(createGenres(genreIds))
                .build();

        Book createdBook = bookRepository.createBook(book);

        return buildBookDto(createdBook);
    }

    @Override
    public void updateBook(Long bookId, String title, List<Long> authorIds, List<Long> genreIds) {

        Book book = Book.builder()
                .id(bookId)
                .title(title)
                .genres(createGenres(genreIds))
                .authors(buildAuthorList(authorIds))
                .build();

        bookRepository.updateBook(book);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteBook(id);
    }

    @Override
    public BookDto getById(Long id) {
        Book book = bookRepository.getById(id);
        return buildBookDto(book);
    }

    @Override
    public List<BookDto> getByAuthorId(Long authorId) {
        List<Book> books = bookRepository.getByAuthorId(authorId);
        return buildBookDtoList(books);
    }

    @Override
    public List<BookDto> getAll() {
        List<Book> books = bookRepository.getAll();
        return buildBookDtoList(books);
    }

    private List<Genre> createGenres(List<Long> genreIds) {
        List<Genre> genres = new ArrayList<>();
        for (Long genreId : genreIds) {
            Genre genre = Genre.builder().id(genreId).build();
            genres.add(genre);
        }
        return genres;
    }

    private List<Author> buildAuthorList(List<Long> authorIds) {
        List<Author> authors = new ArrayList<>();

        for (Long authorId : authorIds) {
            Author author = Author.builder().id(authorId).build();
            authors.add(author);
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
