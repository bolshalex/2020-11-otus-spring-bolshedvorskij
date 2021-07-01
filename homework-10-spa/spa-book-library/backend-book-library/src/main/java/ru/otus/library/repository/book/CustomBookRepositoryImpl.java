package ru.otus.library.repository.book;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import ru.otus.library.domain.entity.Author;
import ru.otus.library.domain.entity.Book;

import java.util.List;
import java.util.stream.Collectors;

public class CustomBookRepositoryImpl implements CustomBookRepository<Book, String> {
    private final MongoTemplate mongoTemplate;

    public CustomBookRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void putAuthorToBooks(Author author, List<Book> books) {
        if (CollectionUtils.isEmpty(books)) {
            return;
        }

        List<String> bookIds = books.stream()
                .map(book -> book.getId())
                .collect(Collectors.toList());

        Query query = new Query(Criteria.where("_id").in(bookIds).and("authors.$id").ne(author.getId()));
        Update update = new Update().addToSet("authors", author);
        mongoTemplate.updateMulti(query, update, Book.class);


    }
}
