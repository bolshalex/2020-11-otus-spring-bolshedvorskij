package ru.otus.library.repository.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import ru.otus.library.domain.entity.Author;
import ru.otus.library.domain.entity.Book;

public class CustomAuthorRepositoryImpl implements CustomAuthorRepository<String> {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public CustomAuthorRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    @Override
    public void deleteById(String id) {
        deleteBookAuthors(id);
        deleteAuthor(id);
    }

    private void deleteBookAuthors(String authorId) {
        Query query = new Query(Criteria.where("$id").is(authorId));
        Update update = new Update().pull("authors", query);
        mongoTemplate.updateMulti(new Query(), update, Book.class);
    }

    private void deleteAuthor(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        mongoTemplate.remove(query, Author.class);
    }
}
