package ru.otus.library.repository.book;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.domain.entity.Author;
import ru.otus.library.domain.entity.Book;
import ru.otus.library.domain.entity.BookInfo;
import ru.otus.library.domain.entity.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcBookRepository implements BookRepository {
    private final NamedParameterJdbcOperations jdbcOperations;

    @Autowired
    public JdbcBookRepository(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Transactional
    @Override
    public Book createBook(BookInfo bookInfo) {
        Book book = bookInfo.getBook();
        insertBook(book);
        insertBookAuthors(book.getId(), bookInfo.getAuthors());
        insertBookGenres(book.getId(), bookInfo.getGenres());
        return book;
    }

    @Override
    public void addBookAuthors(Book book, List<Author> authors) {
        if (CollectionUtils.isEmpty(authors)) {
            return;
        }
        insertBookAuthors(book.getId(), authors);
    }

    @Override
    public void addBookGenres(Book book, List<Genre> genres) {
        insertBookGenres(book.getId(), genres);

    }

    private void insertBookGenre(Long bookId, Genre genre) {
        String sql = "insert into book_genres(`book_id`,`genre_id`) values(:book_id, :genre_id)";
        MapSqlParameterSource sqlParameters = new MapSqlParameterSource()
                .addValue("book_id", bookId)
                .addValue("genre_id", genre.getId());
        jdbcOperations.update(sql, sqlParameters);
    }

    @Transactional
    @Override
    public void updateBook(BookInfo bookInfo) {
        Book book = bookInfo.getBook();
        updateBook(book);
        updateBookAuthors(book.getId(), bookInfo.getAuthors());
        updateBookGenres(book.getId(), bookInfo.getGenres());
    }

    @Transactional
    @Override
    public void deleteBook(Long id) {
        deleteBookAuthors(id);
        deleteBookGenres(id);
        String sql = "delete from books b where b.id = :book_id";
        MapSqlParameterSource sqlParameters = new MapSqlParameterSource()
                .addValue("book_id", id);
        jdbcOperations.update(sql, sqlParameters);
    }

    @Override
    public Book getById(Long id) {
        String sql = "select b.id, b.title from books b where b.id=:book_id";
        MapSqlParameterSource sqlParameters = new MapSqlParameterSource()
                .addValue(":book_id", id);
        return jdbcOperations.queryForObject(sql, sqlParameters, new BookMapper());
    }

    @Override
    public List<Book> getByAuthorId(Long authorId) {
        String sql = "select b.id, b.title from books b join author_books ab on b.id = ab.book_id " +
                "where ab.author_id=:author_id ";
        MapSqlParameterSource sqlParameters = new MapSqlParameterSource()
                .addValue("author_id", authorId);
        return jdbcOperations.query(sql, sqlParameters, new BookMapper());
    }

    @Override
    public List<Book> getAll() {
        String sql = "select b.id, b.title from books b";
        return jdbcOperations.query(sql, new BookMapper());
    }

    private void insertBook(Book book) {
        String sql = "insert into books (`title`) values (:title)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource sqlParameters = new MapSqlParameterSource()
                .addValue("title", book.getTitle());
        jdbcOperations.update(sql, sqlParameters, keyHolder);
        book.setId(keyHolder.getKeyAs(Long.class));
    }

    private void insertBookGenres(Long bookId, List<Genre> genres) {
        if (CollectionUtils.isEmpty(genres)) {
            return;
        }
        for (Genre genre : genres) {
            insertBookGenre(bookId, genre);
        }
    }

    private void updateBookGenres(Long bookId, List<Genre> genres) {
        if (CollectionUtils.isEmpty(genres)) {
            return;
        }
        deleteBookGenres(bookId);
        for (Genre genre : genres) {
            insertBookGenre(bookId, genre);
        }
    }

    private void deleteBookGenres(Long bookId) {
        String sql = "delete from book_genres bg where bd.book_id = :book_id";
        MapSqlParameterSource sqlParameters = new MapSqlParameterSource()
                .addValue("book_id", bookId);
        jdbcOperations.update(sql, sqlParameters);
    }

    private void updateBook(Book book) {
        String sql = "update books b set b.title = :title where b.id = :book_id";
        MapSqlParameterSource sqlParameters = new MapSqlParameterSource()
                .addValue("title", book.getTitle())
                .addValue("book_id", book.getId());
        jdbcOperations.update(sql, sqlParameters);
    }

    private void updateBookAuthors(Long bookId, List<Author> authors) {
        if (CollectionUtils.isEmpty(authors)) {
            return;
        }
        deleteBookAuthors(bookId);
        insertBookAuthors(bookId, authors);
    }

    private void insertBookAuthors(Long bookId, List<Author> authors) {
        if (CollectionUtils.isEmpty(authors)) {
            return;
        }
        for (Author author : authors) {
            insertBookAuthor(bookId, author.getId());
        }
    }

    private void insertBookAuthor(Long bookId, Long authorId) {
        String sql = "insert into author_books (`book_id`,`author_id`) values (:book_id, :author_id)";
        MapSqlParameterSource sqlParameters = new MapSqlParameterSource()
                .addValue("book_id", bookId)
                .addValue("author_id", authorId);
        jdbcOperations.update(sql, sqlParameters);
    }

    private void deleteBookAuthors(Long bookId) {
        String sql = "delete author_books ab where ab.book_id = :book_id";
        MapSqlParameterSource sqlParameters = new MapSqlParameterSource()
                .addValue("book_id", bookId);
        jdbcOperations.update(sql, sqlParameters);
    }

    private static class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Book.builder()
                    .id(rs.getLong("id"))
                    .title(rs.getString("title"))
                    .build();
        }
    }
}
