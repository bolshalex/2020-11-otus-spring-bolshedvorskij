package ru.otus.library.repository.comment;

import org.springframework.stereotype.Repository;
import ru.otus.library.domain.entity.BookComment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class JpaCommentRepository implements CommentRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void createComment(BookComment comment) {
        em.persist(comment);
    }

    @Override
    public void update(BookComment comment) {
        em.merge(comment);
    }

    @Override
    public BookComment getCommentById(Long id) {
        return em.find(BookComment.class, id);
    }

    @Override
    public List<BookComment> getBookComments(Long bookId) {
        TypedQuery<BookComment> query =
                em.createQuery("select b from BookComment b where b.book.id=:bookId", BookComment.class)
                        .setParameter("bookId", bookId);

        return query.getResultList();
    }

    @Override
    public void delete(Long id) {
        Query query = em.createQuery("delete from BookComment b where b.id=:id")
                .setParameter("id", id);

        query.executeUpdate();
    }
}
