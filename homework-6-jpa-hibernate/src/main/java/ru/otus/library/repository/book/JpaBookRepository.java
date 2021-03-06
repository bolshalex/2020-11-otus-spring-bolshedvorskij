package ru.otus.library.repository.book;

import org.springframework.stereotype.Repository;
import ru.otus.library.domain.entity.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class JpaBookRepository implements BookRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Book createBook(Book book) {
        em.persist(book);
        em.flush();
        return book;
    }

    @Override
    public void updateBook(Book book) {
        em.merge(book);
    }

    @Override
    public void deleteBook(Long id) {
        Query query = em.createQuery("delete from Book b where b.id=:bookId")
                .setParameter("bookId", id);
        query.executeUpdate();
    }

    @Override
    public Book getById(Long id) {
        return em.find(Book.class, id);
    }

    @Override
    public List<Book> getByAuthorId(Long authorId) {
        TypedQuery<Book> query =
                em.createQuery("select b from Book b join b.authors a where a.id=:authorId", Book.class)
                        .setParameter("authorId", authorId);

        return query.getResultList();
    }

    @Override
    public List<Book> getAll() {
        TypedQuery<Book> query =
                em.createQuery("select b from Book b", Book.class);
        return query.getResultList();
    }
}
