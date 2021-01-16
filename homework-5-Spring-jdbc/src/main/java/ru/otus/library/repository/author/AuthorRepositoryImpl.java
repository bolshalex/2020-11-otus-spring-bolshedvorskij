package ru.otus.library.repository.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.library.domain.entity.Author;

import java.util.List;

@Repository
public class AuthorRepositoryImpl implements AuthorRepository {
    private final NamedParameterJdbcOperations jdbcOperations;

    @Autowired
    public AuthorRepositoryImpl(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public Author createAuthor(String name) {
        String sql = "insert into authors (`name`) values(:name)";
        MapSqlParameterSource sqlParameters = new MapSqlParameterSource()
                .addValue("name", name);
        jdbcOperations.update(sql,)
        return null;
    }

    @Override
    public void updateAuthor(Long id, String name, List<Long> bookIds) {

    }

    @Override
    public void deleteAuthor(Long id) {

    }

    @Override
    public void getById(Long id) {

    }

    @Override
    public List<Author> getAll() {
        return null;
    }
}
