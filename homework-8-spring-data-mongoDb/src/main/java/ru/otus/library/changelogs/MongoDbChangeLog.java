package ru.otus.library.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mongodb.client.MongoDatabase;
import org.codehaus.plexus.util.IOUtil;
import ru.otus.library.domain.entity.Author;
import ru.otus.library.domain.entity.Book;
import ru.otus.library.domain.entity.BookComment;
import ru.otus.library.domain.entity.Genre;
import ru.otus.library.repository.author.AuthorRepository;
import ru.otus.library.repository.book.BookRepository;
import ru.otus.library.repository.comment.CommentRepository;
import ru.otus.library.repository.genre.GenreRepository;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

@ChangeLog
public class MongoDbChangeLog {
    private static final String GENRES_PATH = "/changeSet/genres.json";
    private static final String AUTHORS_PATH = "/changeSet/authors.json";
    private static final String BOOKS_PATH = "/changeSet/books.json";
    private static final String BOOK_COMMENTS = "/changeSet/comments.json";
    private final Gson gson = new Gson();

    @ChangeSet(order = "01", id = "dropDb", author = "mongock", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "02", id = "insertGenres", author = "mongock", runAlways = true)
    public void insertGenres(GenreRepository genreRepository) {
        Type type = new TypeToken<List<Genre>>() {
        }.getType();

        List<Genre> genres = loadList(GENRES_PATH, type);
        genreRepository.saveAll(genres);
    }

    @ChangeSet(order = "03", id = "insertAuthors", author = "mongock", runAlways = true)
    public void insertAuthors(AuthorRepository authorRepository) {
        Type type = new TypeToken<List<Author>>() {
        }.getType();

        List<Author> authors = loadList(AUTHORS_PATH, type);
        authorRepository.saveAll(authors);
    }

    @ChangeSet(order = "04", id = "insertBooks", author = "mongock", runAlways = true)
    public void insertBooks(BookRepository bookRepository) {
        Type type = new TypeToken<List<Book>>() {
        }.getType();
        List<Book> books = loadList(BOOKS_PATH, type);
        bookRepository.saveAll(books);

    }

    @ChangeSet(order = "05", id = "insertBookComments", author = "mongock", runAlways = true)
    public void insertComments(CommentRepository commentRepository) {
        Type type = new TypeToken<List<BookComment>>() {
        }.getType();
        List<BookComment> bookComments = loadList(BOOK_COMMENTS, type);
        commentRepository.saveAll(bookComments);
    }

    private <E> List<E> loadList(String path) {
        String stringResource;

        try {
            stringResource = IOUtil.toString(getClass().getResourceAsStream(path));
        } catch (IOException e) {
            throw new RuntimeException("Can't load " + path);
        }
        Type type = new TypeToken<List<E>>() {
        }.getType();
        return gson.fromJson(stringResource, type);

    }

    private <E> List<E> loadList(String path, Type type) {
        String stringResource;

        try {
            stringResource = IOUtil.toString(getClass().getResourceAsStream(path));
        } catch (IOException e) {
            throw new RuntimeException("Can't load " + path);
        }
        return gson.fromJson(stringResource, type);
    }
}
