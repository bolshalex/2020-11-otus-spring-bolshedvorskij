package ru.otus.library.repository.genre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import ru.otus.library.domain.entity.Book;
import ru.otus.library.domain.entity.Genre;


public class CustomGenreRepositoryImpl implements CustomGenreRepository<String> {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public CustomGenreRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void deleteById(String genreId) {
        deleteBookGenre(genreId);
        deleteGenre(genreId);
    }

    private void deleteBookGenre(String genreId) {
        Query query = new Query(Criteria.where("$id").is(genreId));
        Update update = new Update().pull("genres", query);
        mongoTemplate.updateMulti(new Query(), update, Book.class);
    }

    private void deleteGenre(String genreId) {
        Query query = new Query(Criteria.where("_id").is(genreId));
        mongoTemplate.remove(query, Genre.class);
    }
}
