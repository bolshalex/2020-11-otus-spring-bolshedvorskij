package ru.otus.library.repository.author;

import org.springframework.stereotype.Repository;
import ru.otus.library.domain.entity.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class JpaAuthorRepository implements AuthorRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Author createAuthor(Author author) {
        em.persist(author);
        em.flush();
        return author;
    }

    @Override
    public void updateAuthor(Author author) {
        em.merge(author);
    }

    @Override
    public void deleteAuthor(Long id) {
        Query query = em.createQuery("delete from Author a where a.id=:authorId")
                .setParameter("authorId", id);
        query.executeUpdate();

    }

    @Override
    public Author getById(Long id) {
        return em.find(Author.class, id);
    }

    @Override
    public Author getByName(String name) {
        TypedQuery<Author> query =
                em.createQuery("select a from Author a where a.name=:name", Author.class)
                        .setParameter("name", name);
        return query.getSingleResult();
    }

    @Override
    public List<Author> getByBookId(Long bookId) {

        TypedQuery<Author> query =
                em.createQuery("select a from Author a join a.books b where b.id=:bookId ", Author.class)
                        .setParameter("bookId", bookId);

        return query.getResultList();
    }

    @Override
    public List<Author> getAll() {
        TypedQuery<Author> query = em.createQuery("select a from Author a ", Author.class);
        return query.getResultList();
    }
}
