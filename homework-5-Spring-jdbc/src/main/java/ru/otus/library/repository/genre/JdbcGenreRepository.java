package ru.otus.library.repository.genre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.domain.entity.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcGenreRepository implements GenreRepository {
    private final NamedParameterJdbcOperations jdbcOperations;

    @Autowired
    public JdbcGenreRepository(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public Genre createGenre(Genre genre) {
        String sql = "insert into genres (`name`) values(:name)";
        MapSqlParameterSource sqlParameters = new MapSqlParameterSource()
                .addValue("name", genre.getName());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(sql, sqlParameters, keyHolder);
        genre.setId(keyHolder.getKeyAs(Long.class));
        return genre;
    }

    @Override
    public void updateGenre(Genre genre) {
        String sql = "update genres g set g.name = :name where g.id = :id";
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("id", genre.getId())
                .addValue("name", genre.getName());
        jdbcOperations.update(sql, sqlParameterSource);
    }

    @Transactional
    @Override
    public void deleteGenre(Long id) {
        deleteGenreBooks(id);
        String sql = "delete from genres g where g.id = :genre_id";
        MapSqlParameterSource sqlParameters = new MapSqlParameterSource()
                .addValue("genre_id", id);
        jdbcOperations.update(sql, sqlParameters);
    }

    @Override
    public Genre getById(Long id) {
        String sql = "select g.id, g.name from genres g where g.id=:id";
        MapSqlParameterSource sqlParameters = new MapSqlParameterSource()
                .addValue("id", id);
        return jdbcOperations.queryForObject(sql, sqlParameters, new GenreMapper());
    }

    @Override
    public List<Genre> getByBookId(Long bookId) {
        String sql = "select g.id, g.name from genres g join book_genres bg on bg.genre_id = g.id " +
                "where bg.book_id = :book_id";

        MapSqlParameterSource sqlParameters = new MapSqlParameterSource()
                .addValue("book_id", bookId);
        return jdbcOperations.query(sql, sqlParameters, new GenreMapper());
    }

    @Override
    public List<Genre> getAll() {
        String sql = "select g.id, g.name from genres g";
        return jdbcOperations.query(sql, new GenreMapper());
    }

    private void deleteGenreBooks(Long id) {
        String sql = "delete from book_genres bg where bg.genre_id = :genre_id";
        MapSqlParameterSource sqlParameters = new MapSqlParameterSource()
                .addValue("genre_id", id);
        jdbcOperations.update(sql, sqlParameters);
    }

    private static class GenreMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Genre(rs.getLong("id"), rs.getString("name"));
        }
    }
}
