package ru.otus.library.repository.author;

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
import ru.otus.library.domain.entity.AuthorInfo;
import ru.otus.library.domain.entity.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcAuthorRepository implements AuthorRepository {
    private final NamedParameterJdbcOperations jdbcOperations;

    @Autowired
    public JdbcAuthorRepository(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Transactional
    @Override
    public Author createAuthor(AuthorInfo authorInfo) {
        Author author = authorInfo.getAuthor();
        insertAuthor(author);
        insertAuthorBooks(author.getId(), authorInfo.getBooks());
        return author;
    }

    @Transactional
    @Override
    public void updateAuthor(AuthorInfo authorInfo) {
        Author author = authorInfo.getAuthor();
        updateAuthor(author);
        updateAuthorBooks(author.getId(), authorInfo.getBooks());
    }

    @Transactional
    @Override
    public void deleteAuthor(Long id) {
        deleteAuthorBooks(id);
        String sql = "delete authors a where a.id = :id";
        MapSqlParameterSource sqlParameters = new MapSqlParameterSource()
                .addValue("id", id);
        jdbcOperations.update(sql, sqlParameters);
    }

    @Override
    public Author getById(Long id) {
        String sql = "select a.id, a.name from authors a where a.id=:id";
        MapSqlParameterSource mapSqlParameters = new MapSqlParameterSource()
                .addValue("id", id);

        return jdbcOperations.queryForObject(sql, mapSqlParameters, new AuthorMapper());
    }

    @Override
    public List<Author> getByBookId(Long bookId) {
        String sql = "select a.id, a.name from authors a join author_books ab on a.id = ab.author_id " +
                "where ab.book_id=:book_id";
        MapSqlParameterSource sqlParameters = new MapSqlParameterSource()
                .addValue(":book_id", bookId);
        return jdbcOperations.query(sql, sqlParameters, new AuthorMapper());
    }

    @Override
    public List<Author> getAll() {
        String sql = "select a.id, a.name from authors a ";
        return jdbcOperations.query(sql, new AuthorMapper());
    }

    private void insertAuthor(Author author) {
        String sql = "insert into authors (`name`) values(:name)";
        MapSqlParameterSource sqlParameters = new MapSqlParameterSource()
                .addValue("name", author.getName());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(sql, sqlParameters, keyHolder);
        author.setId(keyHolder.getKeyAs(Long.class));
    }

    private void updateAuthor(Author author) {
        String sql = "update authors a  set a.name = :name where a.id = :id";
        MapSqlParameterSource sqlParameters = new MapSqlParameterSource()
                .addValue("id", author.getId())
                .addValue("name", author.getName());
        jdbcOperations.update(sql, sqlParameters);
    }

    private void updateAuthorBooks(Long authorId, List<Book> books) {
        deleteAuthorBooks(authorId);
        insertAuthorBooks(authorId, books);
    }

    private void insetAuthorBook(Long authorId, Long bookId) {
        String sql = "insert into author_books (`book_id`, `author_id`)  values (:book_id, :author_id)";
        MapSqlParameterSource sqlParameters = new MapSqlParameterSource()
                .addValue("book_id", bookId)
                .addValue("author_id", authorId);
        jdbcOperations.update(sql, sqlParameters);
    }


    private void deleteAuthorBooks(Long authorId) {
        String sql = "delete from author_books ab where ab.author_id=:author_id";
        MapSqlParameterSource sqlParameters = new MapSqlParameterSource()
                .addValue("author_id", authorId);
        jdbcOperations.update(sql, sqlParameters);
    }

    private void insertAuthorBooks(Long authorId, List<Book> books) {
        if (CollectionUtils.isEmpty(books)) {
            return;
        }
        for (Book book : books) {
            insetAuthorBook(authorId, book.getId());
        }
    }
    private static class AuthorMapper implements RowMapper<Author> {


        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Author(rs.getLong("id"), rs.getString("name"));
        }
    }
}
